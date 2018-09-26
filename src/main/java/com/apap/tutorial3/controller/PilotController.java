package com.apap.tutorial3.controller;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * Created by esak on 9/26/2018.
 */

@Controller
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @RequestMapping("/pilot/add")
    public String add(@RequestParam(value = "id", required = true) String id,
                      @RequestParam(value = "licenseNumber", required = true) String licenseNumber,
                      @RequestParam(value = "name", required = true) String name,
                      @RequestParam(value = "flyHour", required = true) int flyHour) {
        PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
        pilotService.addPilot(pilot);
        return "add";
    }

    @RequestMapping("/pilot/view")
    public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

        model.addAttribute("pilot", archive);
        return "view-pilot";
    }

    @RequestMapping("/pilot/viewall")
    public String viewall(Model model) {
        List<PilotModel> archive = pilotService.getPilotList();

        model.addAttribute("pilots", archive);
        return "viewall-pilot";
    }

    @RequestMapping(value = {"/pilot/view/license-number", "/pilot/view/license-number/{licenseNumber}"})
    public String viewlicense(@PathVariable Optional<String> licenseNumber,
                              Model model) {
        List<PilotModel> archive = pilotService.getPilotList();

        if (!licenseNumber.isPresent()) {
            model.addAttribute("msg", "Nomor Lisensi Kosong");
            return "errorpage";
        } else {
            for (PilotModel pilots : archive) {
                if (pilots.getLicenseNumber().equals(licenseNumber.get())) {
                    model.addAttribute("pilot", pilots);
                    return "view-pilot";
                }
            }
            model.addAttribute("msg", "Nomor Lisensi Tidak Ditemukan");
            return "errorpage";
        }
    }

    @RequestMapping(value = {"/pilot/update/license-number","/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}"})
    public String updateFlyHour(@PathVariable Optional<String> licenseNumber,
                                @PathVariable Optional<Integer> flyHour,
                                Model model) {
        List<PilotModel> archive = pilotService.getPilotList();

        if (!licenseNumber.isPresent()) {
            model.addAttribute("msg", "Nomor Lisensi Kosong");
            return "errorpage";
        } else {
            for (PilotModel pilots : archive) {
                if (pilots.getLicenseNumber().equals(licenseNumber.get())) {
                    pilots.setFlyHour(flyHour.get());
                    return "update";
                }
            }
            model.addAttribute("msg", "Nomor Lisensi Tidak Ditemukan");
            return "errorpage";
        }

    }

    @RequestMapping(value = {"pilot/delete/id","/pilot/delete/id/{id}"})
    public String deletePilot(@PathVariable Optional<String> id,
                              Model model) {
        List<PilotModel> archive = pilotService.getPilotList();

        if (!id.isPresent()) {
            String msg = "Nomor Lisensi Kosong";
            model.addAttribute("msg", msg);
            return "errorpage";
        } else {
            for (PilotModel pilots : archive) {
                if (pilots.getId().equals(id.get())) {
                    model.addAttribute("namapilot",pilots.getName());
                    archive.remove(pilots);
                    return "delete";
                }
            }
            model.addAttribute("msg", "Nomor Lisensi Tidak Ditemukan");
            return "errorpage";
        }

    }


}
