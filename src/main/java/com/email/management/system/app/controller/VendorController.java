package com.email.management.system.app.controller;


import com.email.management.system.app.model.Vendor;
import com.email.management.system.app.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/addVendor")
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.saveVendor(vendor);
    }

    @GetMapping("/getVendor")
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }
}