package se.kth.id1212.appserv.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.id1212.appserv.converter.domain.Conversion;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ConverterRepository extends JpaRepository<Conversion, String> {

    Conversion findConversionByFromto(String fromto);

    @Modifying
    //@Query(value = "update CONVERSION set CON_RATE=rate where CON_FROMTO=fromto", nativeQuery = true)
    @Query(value = "UPDATE CONVERSION SET CON_RATE = ? WHERE CON_FROMTO= ?", nativeQuery = true)
    void setNewRate(double rate, String fromto);
}
