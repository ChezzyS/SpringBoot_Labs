package ru.arkhipov.lab7.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.arkhipov.lab7.dao.StudyGroupRepository;
import ru.arkhipov.lab7.entity.StudyGroup;

import java.util.Optional;

@Slf4j
@Controller
public class StudyGroupController {

    private final StudyGroupRepository studyGroupRepository;

    @Autowired
    public StudyGroupController(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    @GetMapping("/groups")
    public ModelAndView getAllGroups() {
        ModelAndView mav = new ModelAndView("list-groups");
        mav.addObject("groups", studyGroupRepository.findAll());
        return mav;
    }

    @GetMapping("/addGroupForm")
    public ModelAndView addGroupForm() {
        ModelAndView mav = new ModelAndView("add-group-form");
        mav.addObject("group", new StudyGroup());
        return mav;
    }

    @PostMapping("/saveGroup")
    public RedirectView saveGroup(@ModelAttribute("group") StudyGroup group) {
        studyGroupRepository.save(group);
        log.info("Сохранена учебная группа: {}", group);
        return new RedirectView("groups");
    }

    @GetMapping("/showGroupUpdateForm")
    public ModelAndView showGroupUpdateForm(@RequestParam Long groupId) {
        ModelAndView mav = new ModelAndView("add-group-form");
        Optional<StudyGroup> optionalGroup = studyGroupRepository.findById(groupId);
        StudyGroup group = optionalGroup.orElseGet(StudyGroup::new);
        mav.addObject("group", group);
        return mav;
    }

    @GetMapping("/deleteGroup")
    public RedirectView deleteGroup(@RequestParam Long groupId) {
        studyGroupRepository.deleteById(groupId);
        log.info("Удалена учебная группа с id={}", groupId);
        return new RedirectView("groups");
    }
}
