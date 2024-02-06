package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ThreadSafe
@AllArgsConstructor
@Controller
@RequestMapping("/accident")
public class AccidentController {
    private final AccidentServiceSpringData accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleServiceSpringData ruleService;

    @GetMapping("/accidentListPage")
    public String viewAccidentList(Model model) {
        model.addAttribute("accidents", accidentService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        return "accident/accidentList";
    }

    @GetMapping("/createAccidentPage")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.getAll());
        return "accident/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(accidentTypeService.findById(accident.getType().getId()).get());
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(ruleService.getRulesByIds(ids));
        accidentService.create(accident);
        return "redirect:/accident/accidentListPage";
    }

    @GetMapping("/updateAccidentPage/{id}")
    public String viewUpdateAccident(Model model, @PathVariable int id) {
        Optional<Accident> accident = accidentService.findById(id);
        if (accident.isEmpty()) {
            model.addAttribute("message", "Инцидент не найден.");
            return "errors/404";
        }
        model.addAttribute("accident", accident.get());
        return "accident/updateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model) {
        boolean rsl = accidentService.update(accident);
        if (!rsl) {
            model.addAttribute("message", "Инцидент не найден.");
            return "errors/404";
        }
        return "redirect:/accident/accidentListPage";
    }
}
