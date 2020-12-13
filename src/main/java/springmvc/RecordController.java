package springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping
@SessionAttributes("name")
public class RecordController {

    StudentService studentService;
    ConnectionWrapper connectionWrapper = new ConnectionWrapper();

    //Initial 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String show(ModelMap modelMap) throws SQLException, ClassNotFoundException {
        studentService = new StudentService(connectionWrapper.connect());
        modelMap.addAttribute("records", studentService.displayRecord());
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(ModelMap modelMap) throws SQLException, ClassNotFoundException {
        studentService = new StudentService(connectionWrapper.connect());
        modelMap.addAttribute("records", studentService.displayRecord());
        System.out.println(studentService.displayRecord().size());
        return "login";
    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.GET)
    public String showAddForm(ModelMap modelMap) throws SQLException, ClassNotFoundException {
        studentService = new StudentService(connectionWrapper.connect());
        return "addRecord";
    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    public String deleteTodos(ModelMap modelMap,  @RequestParam String snumber, @RequestParam String sname, @RequestParam String gpa) throws SQLException, ClassNotFoundException {
        studentService = new StudentService(connectionWrapper.connect());
       StudentData studentData = new StudentData(snumber, sname, Double.parseDouble(gpa));
       studentService.addRecord(studentData);
        return "redirect:/login";
    }

    @RequestMapping(value = "/delete-record", method = RequestMethod.GET)
    public String deleteRecord(ModelMap modelMap, @RequestParam String snumber) throws SQLException, ClassNotFoundException {
        studentService = new StudentService(connectionWrapper.connect());
        //Call the delete function from the service class
        studentService.deleteRecord(snumber);
        modelMap.addAttribute("records", studentService.displayRecord());
        System.out.println(studentService.displayRecord().size());
        return "redirect:/login";
    }

    @RequestMapping(value = "/update-record", method = RequestMethod.GET)
    public String updateRecord(ModelMap modelMap, @RequestParam String snumber) throws SQLException, ClassNotFoundException {
        studentService = new StudentService(connectionWrapper.connect());
        //First, Call the delete function from the service class then show the add page
        studentService.deleteRecord(snumber);
        return "redirect:/addRecord";
    }










}
