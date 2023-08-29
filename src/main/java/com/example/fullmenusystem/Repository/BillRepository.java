package com.example.fullmenusystem.Repository;

import com.example.fullmenusystem.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
  Bill findBillById(Integer id);
}
