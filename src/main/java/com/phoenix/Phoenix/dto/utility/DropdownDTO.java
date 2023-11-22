package com.phoenix.Phoenix.dto.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DropdownDTO {
    private Object value;
    private String textContent;

    public static List<DropdownDTO> getTypeDropdown(){
        var type = new LinkedList<DropdownDTO>();
        type.add(new DropdownDTO("RSB", "Regular Single Bed"));
        type.add(new DropdownDTO("RDB", "Regular Double Bed"));
        type.add(new DropdownDTO("RTB", "Regular Twin Bed"));
        type.add(new DropdownDTO("VSB", "VIP Single Bed"));
        type.add(new DropdownDTO("VDB", "VIP Double Bed"));
        type.add(new DropdownDTO("VTB", "VIP Twin Bed"));
        return type;
    }

    public static List<DropdownDTO> getStatusDropdown(){
        var status = new LinkedList<DropdownDTO>();
        status.add(new DropdownDTO("Available", "Available"));
        status.add(new DropdownDTO("Booking", "Booking"));
        return status;
    }

    public static List<DropdownDTO> getPaymentMethodDropdown(){
        var payment = new LinkedList<DropdownDTO>();
        payment.add(new DropdownDTO("CC   ", "Credit Card"));
        payment.add(new DropdownDTO("DD   ", "Direct Debit"));
        payment.add(new DropdownDTO("VO   ", "Voucher"));
        payment.add(new DropdownDTO("EM   ", "Electronic Money"));
        payment.add(new DropdownDTO("CA   ", "Cash"));
        return payment;
    }

    public static List<DropdownDTO> getReservationMethodDropdown(){
        var method = new LinkedList<DropdownDTO>();
        method.add(new DropdownDTO("OW   ", "Official Web"));
        method.add(new DropdownDTO("OS   ", "On Site"));
        method.add(new DropdownDTO("AW   ", "Travel Agent Web atau App"));
        return method;
    }

    public static List<DropdownDTO> getRole(){
        var role = new LinkedList<DropdownDTO>();
        role.add(new DropdownDTO("Administrators", "Administrators"));
        role.add(new DropdownDTO("Administrators", "Administrators"));
        return role;
    }
}
