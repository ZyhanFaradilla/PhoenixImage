package com.phoenix.Phoenix.service;

import com.phoenix.Phoenix.dao.ReservationLogRepository;
import com.phoenix.Phoenix.utility.MapperHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LandingAdminService {
    @Autowired
    private ReservationLogRepository reservationLogRepository;

    public List<Integer> getReservationYears() {
        return reservationLogRepository.getReservationYears();
    }

    public List<Object> getAnnualIncome(Integer year) {
        return reservationLogRepository.executeAnnualIncome(year);
    }

    public Double getTotalIncome(List<Object> data) {
        var totalIncome = new BigDecimal(0.0);
        for(var datum : data){
            var periodIncome = MapperHelper.getBigDecimalField(datum, 2);
            totalIncome = totalIncome.add(periodIncome);
        }
        return totalIncome.doubleValue();
    }

    public Double getFluctuation(List<Object> data, Integer year) {
        var totalIncome = getTotalIncome(data);
        var lastYearTotalIncome = getTotalIncome(getAnnualIncome(year-1));
        return totalIncome - lastYearTotalIncome;
    }

    public String getHighestPeriod(List<Object> data) {
        var highestPeriod = "No Record";
        var highestIncome = 0.0;
        for(var datum : data){
            var periodIncome = MapperHelper.getBigDecimalField(datum, 2).doubleValue();
            if(periodIncome > highestIncome){
                highestIncome = periodIncome;
                highestPeriod = MapperHelper.getStringField(datum, 1);
            }
        }
        return highestPeriod;
    }

    public String getLowestPeriod(List<Object> data) {
        var lowestPeriod = MapperHelper.getStringField(data.get(0), 1);;
        var lowestIncome = MapperHelper.getBigDecimalField(data.get(0), 2).doubleValue();
        for(var datum : data){
            var periodIncome = MapperHelper.getBigDecimalField(datum, 2).doubleValue();
            if(periodIncome < lowestIncome){
                lowestIncome = periodIncome;
                lowestPeriod = MapperHelper.getStringField(datum, 1);
            }
        }
        return lowestPeriod;
    }
}
