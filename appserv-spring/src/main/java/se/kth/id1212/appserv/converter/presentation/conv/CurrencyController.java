package se.kth.id1212.appserv.converter.presentation.conv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.id1212.appserv.converter.application.ConverterService;
import se.kth.id1212.appserv.converter.domain.ConversionDTO;
import se.kth.id1212.appserv.converter.presentation.error.ExceptionHandlers;

import javax.validation.Valid;

@Controller
@Scope("session")
public class CurrencyController {
    static final String DEFAULT_PAGE_URL = "/";
    static final String SELECT_CON_PAGE_URL = "select-conversion";
    static final String UPDATE_RATE_URL = "update-rate";

    private static final String CURRENT_CON_OBJ_NAME = "currentConversion";
    private static final String FIND_CONVERSION_FORM_OBJ_NAME = "findConversionForm";
    private static final String UPDATE_RATE_FORM_OBJ_NAME = "updateRateForm";

    private String conversionResult;

    @Autowired
    private ConverterService service;
    private ConversionDTO currentConv;

    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return "redirect:" + SELECT_CON_PAGE_URL;
    }

    @GetMapping("/" + SELECT_CON_PAGE_URL)
    public String showCurrencySelectionView(FindConversionForm findConversionForm) {
        return SELECT_CON_PAGE_URL;
    }

    private String showConversionResultPage(Model model) {
        if (currentConv != null) {
            model.addAttribute(CURRENT_CON_OBJ_NAME, currentConv);
        }
        return SELECT_CON_PAGE_URL;
    }

    @PostMapping("/" + SELECT_CON_PAGE_URL)
    public String findExchangeRate(@Valid FindConversionForm findConversionForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(FIND_CONVERSION_FORM_OBJ_NAME, new FindConversionForm());
            return SELECT_CON_PAGE_URL;
        }

        String from = findConversionForm.getFrom();
        int amount = findConversionForm.getNumber();
        String to = findConversionForm.getTo();
        currentConv = service.findConversion(from + "" + to);
        double rate = currentConv.getRate();
        findConversionForm.setConversionResult(rate * amount);
        conversionResult = findConversionForm.getConversionResult();

        if (currentConv == null) {
            model.addAttribute(ExceptionHandlers.ERROR_TYPE_KEY, ExceptionHandlers.NO_CONVERSION_FOUND);
            model.addAttribute(ExceptionHandlers.ERROR_INFO_KEY, ExceptionHandlers.NO_CONVERSION_FOUND_INFO);
            return ExceptionHandlers.ERROR_PAGE_URL;
        }
        return showConversionResultPage(model);
    }

    private String showUpdateRateResultPage(Model model) {
        if (currentConv != null) {
            System.out.println("in showupdaaterespage ");
            model.addAttribute(CURRENT_CON_OBJ_NAME, currentConv);
        }
        System.out.println("currentconv är inte not null iaf ");
        return UPDATE_RATE_URL;
    }

    @GetMapping("/" + UPDATE_RATE_URL)
    public String showUpdateRateView(FindConversionForm findConversionForm) {
        return UPDATE_RATE_URL;
    }

    @PostMapping("/" + UPDATE_RATE_URL)
    public String setExchangeRate(@Valid FindConversionForm findConversionForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(CURRENT_CON_OBJ_NAME, new FindConversionForm());
            System.out.println("bindingRes has errors!");
            return UPDATE_RATE_URL;
        }

        String from = findConversionForm.getFrom();
        double rate = findConversionForm.getNumber();
        String to = findConversionForm.getTo();
        service.setNewRate(rate,from + "" + to);
        System.out.println("LINE 99");
        currentConv.setNewRate(rate);
        System.out.println("LINE 101");
        if (currentConv == null) {
            model.addAttribute(ExceptionHandlers.ERROR_TYPE_KEY, ExceptionHandlers.NO_CONVERSION_FOUND);
            model.addAttribute(ExceptionHandlers.ERROR_INFO_KEY, ExceptionHandlers.NO_CONVERSION_FOUND_INFO);
            return ExceptionHandlers.ERROR_PAGE_URL;
        }
        return showUpdateRateResultPage(model);
    }
}
