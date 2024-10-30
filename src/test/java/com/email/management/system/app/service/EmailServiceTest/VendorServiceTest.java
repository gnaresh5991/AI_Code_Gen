package com.email.management.system.app.service.EmailServiceTest;

import com.email.management.system.app.model.Vendor;
import com.email.management.system.app.repository.VendorRepository;
import com.email.management.system.app.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VendorServiceTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveVendor() {
        // Arrange
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Vendor One");
        vendor.setEmail("vendor.one@example.com");
        vendor.setUpi("vendorone@upi");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        // Act
        Vendor savedVendor = vendorService.saveVendor(vendor);

        // Assert
        assertEquals(vendor.getId(), savedVendor.getId());
        assertEquals(vendor.getName(), savedVendor.getName());
        assertEquals(vendor.getEmail(), savedVendor.getEmail());
        assertEquals(vendor.getUpi(), savedVendor.getUpi());
        verify(vendorRepository, times(1)).save(any(Vendor.class));
    }

    @Test
    void testGetAllVendors() {
        // Arrange
        Vendor vendor1 = new Vendor(1L, "vendor.one@example.com", "Vendor One", "vendorone@upi");
        Vendor vendor2 = new Vendor(2L, "vendor.two@example.com", "Vendor Two", "vendortwo@upi");

        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);
        when(vendorRepository.findAll()).thenReturn(vendors);

        // Act
        List<Vendor> result = vendorService.getAllVendors();

        // Assert
        assertEquals(2, result.size());
        assertEquals(vendor1, result.get(0));
        assertEquals(vendor2, result.get(1));
        verify(vendorRepository, times(1)).findAll();
    }
}
