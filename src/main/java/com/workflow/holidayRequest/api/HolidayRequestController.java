package com.workflow.holidayRequest.api;

import com.workflow.holidayRequest.dto.HolidayRequest;
import com.workflow.holidayRequest.dto.ProcessInstanceResponse;
import com.workflow.holidayRequest.dto.Details;
import com.workflow.holidayRequest.service.HolidayService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class HolidayRequestController {
    HolidayService holidayService;

    //********************************************************** deployment & process endpoints **************************************

    /**
     *
     * @param holidayRequest
     * @return
     */
    @PostMapping("/holiday/apply")
    public ProcessInstanceResponse applyHoliday(@RequestBody HolidayRequest holidayRequest) {
        holidayService.deployProcessDefinition();
        return holidayService.applyHoliday(holidayRequest);
    }

    /**
     *
     * @return
     */
    @GetMapping("/employee/tasks")
    public List<Details> getEmployeeTasks() {
        return holidayService.getEmployeeTasks();
    }

    /**
     *
     * @param taskId
     * @param withSubstitute
     */
    @PostMapping("/employee/addSubstitute/tasks/{taskId}/{withSubstitute}")
    public void addSubstitute(@PathVariable("taskId") String taskId, @PathVariable("withSubstitute") Boolean withSubstitute) {
        holidayService.addSubstitute(taskId, withSubstitute);
    }

    /**
     *
     * @return
     */
    @GetMapping("/substitute/tasks")
    public List<Details> getSubstituteTasks() {
        return holidayService.getSubstituteTasks();
    }

    /**
     *
     * @param taskId
     * @param approveSubstitution
     */
    @PostMapping("/substitute/approve/tasks/{taskId}/{approveSubstitution}")
    public void approveSubstituteTask(@PathVariable("taskId") String taskId, @PathVariable("approveSubstitution") Boolean approveSubstitution) {
        holidayService.approveSubstituteTask(taskId, approveSubstitution);
    }

    /**
     *
     * @return
     */
    @GetMapping("/superior/tasks")
    public List<Details> getSuperiorTasks() {
        return holidayService.getSuperiorTasks();
    }

    /**
     *
     * @param taskId
     * @param approve
     */
    @PostMapping("/superior/approve/tasks/{taskId}/{approve}")
    public void approveTask(@PathVariable("taskId") String taskId, @PathVariable("approve") Boolean approve) {
        holidayService.approveHoliday(taskId, approve);
    }

    /**
     *
     * @return
     */
    @GetMapping("/employee/holidays/approved")
    public List<Details> getApprovedHolidayRequests() {
        return holidayService.fetchEmployeeHolidayRequests();
    }

    /**
     *
     * @return
     */
    @GetMapping("/department/hr/holidayrequest/closed")
    public List<Details> getClosedHolidayRequests() {
        return holidayService.fetchClosedHolidayRequests();
    }
}
