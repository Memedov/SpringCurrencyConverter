package se.kth.id1212.appserv.converter.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.id1212.appserv.converter.domain.ConversionDTO;
import se.kth.id1212.appserv.converter.repository.ConverterRepository;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class ConverterService {
    @Autowired
    private ConverterRepository converterRepo;

    public ConversionDTO findConversion(String fromto) {
        System.out.println("converter service, c = " + fromto);
        return converterRepo.findConversionByFromto(fromto);
    }

    public void setNewRate(double value, String fromto) {
        System.out.println("ConvService setNewRate " + fromto + " " +value+"");
        converterRepo.setNewRate(value, fromto);
    }
}
