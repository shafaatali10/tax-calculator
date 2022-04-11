package com.shafaat.seisma.taxcalc.repository;

import com.shafaat.seisma.taxcalc.domain.TaxSlab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxSlabRepository extends JpaRepository<TaxSlab, Long> {}
