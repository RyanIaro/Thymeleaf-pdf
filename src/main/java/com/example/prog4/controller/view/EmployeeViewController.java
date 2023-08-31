package com.example.prog4.controller.view;

import com.example.prog4.conf.company.CompanyConf;
import com.example.prog4.controller.PopulateController;
import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.model.Employee;
import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.model.enums.AgeParameter;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.OutputStream;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.Period;

import static java.time.LocalDate.now;

@Controller
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeViewController extends PopulateController {
    private final TemplateEngine templateEngine;
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @GetMapping("/list")
    public String getAll(
            @ModelAttribute EmployeeFilter filters,
            Model model,
            HttpSession session
    ) {
        model.addAttribute("employees", employeeService.getAll(filters).stream().map(employeeMapper::toView).toList())
                .addAttribute("employeeFilters", filters)
                .addAttribute("directions", Sort.Direction.values());
        session.setAttribute("employeeFiltersSession", filters);

        return "employees";
    }

    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("employee", Employee.builder().build());
        return "employee_creation";
    }

    @GetMapping("/edit/{eId}")
    public String editEmployee(@PathVariable String eId, Model model) {
        Employee toEdit = employeeMapper.toView(employeeService.getOne(eId));
        String cnaps = employeeService.getEmployeeCnaps(eId);
        model.addAttribute("employee", toEdit);
        model.addAttribute("cnaps", cnaps);

        return "employee_edition";
    }

    @GetMapping("/show/{eId}")
    public String showEmployee(@PathVariable String eId, Model model) {
        Employee toShow = employeeMapper.toView(employeeService.getOne(eId));
        String cnaps = employeeService.getEmployeeCnaps(eId);
        model.addAttribute("employee", toShow);
        model.addAttribute("cnaps", cnaps);

        return "employee_show";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/employee/list";
    }

    @GetMapping("/show/{eId}/{type}/pdf")
    public void generateEmployeePdf(@PathVariable AgeParameter type, @PathVariable String eId, HttpServletResponse response) throws Exception {
        Employee employee = employeeMapper.toView(employeeService.getOne(eId));
        String cnaps = employeeService.getEmployeeCnaps(eId);
        CompanyConf company = new CompanyConf();

        if (type == AgeParameter.BIRTHDAY) {
            Period period = Period.between(employee.getBirthDate(), LocalDate.now());
            employee.setAge(period.getYears());
        } else if (type == AgeParameter.YEAR_ONLY) {
            int year = LocalDate.now().getYear() - employee.getBirthDate().getYear();
            employee.setAge(year);
        }

        Context context = new Context();
        context.setVariable("employee", employee);
        context.setVariable("cnaps", cnaps);
        context.setVariable("company", company);
        context.setVariable("type", type);

        StringWriter writer = new StringWriter();
        templateEngine.process("employee_pdf", context, writer);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Employee "+eId+".pdf");

        OutputStream outputStream = response.getOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(writer.toString());
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
