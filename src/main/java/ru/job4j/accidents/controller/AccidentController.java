package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@AllArgsConstructor
@Controller
@RequestMapping("/accident")
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    @GetMapping("/accidentListPage")
    public String viewAccidentList(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "accident/accidentList";
    }

    @GetMapping("/createAccidentPage")
    public String viewCreateAccident(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
        model.addAttribute("types", types);
        return "accident/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accident.setType(accidentTypeService.findById(accident.getType().getId()));
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
