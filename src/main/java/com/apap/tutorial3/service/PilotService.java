package com.apap.tutorial3.service;
import com.apap.tutorial3.model.PilotModel;

import java.util.List;
/**
 * Created by esak on 9/26/2018.
 */


public interface PilotService {
    void addPilot (PilotModel pilot);
    List<PilotModel> getPilotList();
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);

}
