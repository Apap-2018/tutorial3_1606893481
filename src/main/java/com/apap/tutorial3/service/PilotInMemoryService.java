package com.apap.tutorial3.service;

import com.apap.tutorial3.model.PilotModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esak on 9/26/2018.
 */

@Service
public class PilotInMemoryService implements PilotService {
    private List<PilotModel> archivePilot;

    public PilotInMemoryService() {
        archivePilot = new ArrayList<>();
    }

    @Override
    public void addPilot(PilotModel pilot) {
        archivePilot.add(pilot);
    }

    @Override
    public List<PilotModel> getPilotList() {
        return archivePilot;
    }

    @Override
    public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
        for (PilotModel pilots : archivePilot)
            if (pilots.getLicenseNumber().equals(licenseNumber))
                return pilots;
        return null;
    }
}
