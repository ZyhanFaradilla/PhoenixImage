package com.phoenix.Phoenix.dto.roomServices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertRosterRoomServicesDTO {
    private String employeeNumber;
    private LocalTime mondayRosterStart;
    private LocalTime mondayRosterFinish;
    private LocalTime tuesdayRosterStart;
    private LocalTime tuesdayRosterFinish;
    private LocalTime wednesdayRosterStart;
    private LocalTime wednesdayRosterFinish;
    private LocalTime thursdayRosterStart;
    private LocalTime thursdayRosterFinish;
    private LocalTime fridayRosterStart;
    private LocalTime fridayRosterFinish;
    private LocalTime saturdayRosterStart;
    private LocalTime saturdayRosterFinish;
    private LocalTime sundayRosterStart;
    private LocalTime sundayRosterFinish;
}
