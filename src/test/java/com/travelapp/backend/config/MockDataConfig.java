package com.travelapp.backend.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelapp.backend.entities.CustomTrip;
import com.travelapp.backend.entities.CustomTripItem;
import com.travelapp.backend.repositories.CustomTripRepository;
import com.travelapp.backend.services.CustomTripService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomTripServiceTests {
    
    @Autowired
    private CustomTripService customTripService;
    
    @MockBean
    private CustomTripRepository customTripRepository;
    
    // Test retrieveCustomTripByTripId() method
    
    @Test
    public void testRetrieveCustomTripByTripId_Success() {
        int tripId = 1;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        
        CustomTrip customTrip = customTripService.retrieveCustomTripByTripId(tripId);
        
        assertNotNull(customTrip);
    }
    
    @Test
    public void testRetrieveCustomTripByTripId_NotFound() {
        int tripId = 1;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.empty());
        
        CustomTrip customTrip = customTripService.retrieveCustomTripByTripId(tripId);
        
        assertNull(customTrip);
    }
    
    // Test retrieveAllCustomTrips() method
    
    @Test
    public void testRetrieveAllCustomTrips_Success() {
        List<CustomTrip> customTrips = new ArrayList<>();
        customTrips.add(new CustomTrip());
        
        when(customTripRepository.findAll()).thenReturn(customTrips);
        
        List<CustomTrip> retrievedCustomTrips = customTripService.retrieveAllCustomTrips();
        
        assertEquals(1, retrievedCustomTrips.size());
    }
    
    @Test
    public void testRetrieveAllCustomTrips_NotFound() {
        List<CustomTrip> customTrips = new ArrayList<>();
        
        when(customTripRepository.findAll()).thenReturn(customTrips);
        
        List<CustomTrip> retrievedCustomTrips = customTripService.retrieveAllCustomTrips();
        
        assertEquals(0, retrievedCustomTrips.size());
    }
    
    // Test retrieveCustomTripItemBytripIdAndItemId() method
    
    @Test
    public void testRetrieveCustomTripItemBytripIdAndItemId_Success() {
        int tripId = 1;
        int itemId = 2;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        when(customTripRepository.findByTripItemIdAndCustomTripTripId(itemId, tripId))
                .thenReturn(Optional.of(new CustomTripItem()));
        
        CustomTripItem customTripItem = customTripService.retrieveCustomTripItemBytripIdAndItemId(tripId, itemId);
        
        assertNotNull(customTripItem);
    }
    
    @Test
    public void testRetrieveCustomTripItemBytripIdAndItemId_NotFound() {
        int tripId = 1;
        int itemId = 2;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        when(customTripRepository.findByTripItemIdAndCustomTripTripId(itemId, tripId))
                .thenReturn(Optional.empty());
        
        CustomTripItem customTripItem = customTripService.retrieveCustomTripItemBytripIdAndItemId(tripId, itemId);
        
        assertNull(customTripItem);
    }
    
    // Test retrieveAllCustomTripItems() method
    
    @Test
    public void testRetrieveAllCustomTripItems_Success() {
        int tripId = 1;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        
        List<CustomTripItem> customTripItems = new ArrayList<>();
        customTripItems.add(new CustomTripItem());
        
        when(customTripRepository.findAllByCustomTripId(tripId)).thenReturn(customTripItems);
        
        List<CustomTripItem> retrievedCustomTripItems = customTripService.retrieveAllCustomTripItems(tripId);
        
        assertEquals(1, retrievedCustomTripItems.size());
    }
    
    @Test
    public void testRetrieveAllCustomTripItems_NotFound() {
        int tripId = 1;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        
        List<CustomTripItem> customTripItems = new ArrayList<>();
        
        when(customTripRepository.findAllByCustomTripId(tripId)).thenReturn(customTripItems);
        
        List<CustomTripItem> retrievedCustomTripItems = customTripService.retrieveAllCustomTripItems(tripId);
        
        assertEquals(0, retrievedCustomTripItems.size());
    }
    
    // Test retrieveCustomTripItemBytripIdAndItemId() method
    
    @Test
    public void testRetrieveCustomTripItemBytripIdAndItemId_Success() {
        int tripId = 1;
        int itemId = 2;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        when(customTripRepository.findByTripItemIdAndCustomTripTripId(itemId, tripId))
                .thenReturn(Optional.of(new CustomTripItem()));
        
        CustomTripItem customTripItem = customTripService.retrieveCustomTripItemBytripIdAndItemId(tripId, itemId);
        
        assertNotNull(customTripItem);
    }
    
    @Test
    public void testRetrieveCustomTripItemBytripIdAndItemId_NotFound() {
        int tripId = 1;
        int itemId = 2;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        when(customTripRepository.findByTripItemIdAndCustomTripTripId(itemId, tripId))
                .thenReturn(Optional.empty());
        
        CustomTripItem customTripItem = customTripService.retrieveCustomTripItemBytripIdAndItemId(tripId, itemId);
        
        assertNull(customTripItem);
    }
    
    // Test retrieveAllCustomTripItems() method
    
    @Test
    public void testRetrieveAllCustomTripItems_Success() {
        int tripId = 1;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        
        List<CustomTripItem> customTripItems = new ArrayList<>();
        customTripItems.add(new CustomTripItem());
        
        when(customTripRepository.findAllByCustomTripId(tripId)).thenReturn(customTripItems);
        
        List<CustomTripItem> retrievedCustomTripItems = customTripService.retrieveAllCustomTripItems(tripId);
        
        assertEquals(1, retrievedCustomTripItems.size());
    }
    
    @Test
    public void testRetrieveAllCustomTripItems_NotFound() {
        int tripId = 1;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        
        List<CustomTripItem> customTripItems = new ArrayList<>();
        
        when(customTripRepository.findAllByCustomTripId(tripId)).thenReturn(customTripItems);
        
        List<CustomTripItem> retrievedCustomTripItems = customTripService.retrieveAllCustomTripItems(tripId);
        
        assertEquals(0, retrievedCustomTripItems.size());
    }
    
    // Test retrieveCustomTripItemBytripIdAndItemId() method
    
    @Test
    public void testRetrieveCustomTripItemBytripIdAndItemId_Success() {
        int tripId = 1;
        int itemId = 2;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        when(customTripRepository.findByTripItemIdAndCustomTripTripId(itemId, tripId))
                .thenReturn(Optional.of(new CustomTripItem()));
        
        CustomTripItem customTripItem = customTripService.retrieveCustomTripItemBytripIdAndItemId(tripId, itemId);
        
        assertNotNull(customTripItem);
    }
    
    @Test
    public void testRetrieveCustomTripItemBytripIdAndItemId_NotFound() {
        int tripId = 1;
        int itemId = 2;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        when(customTripRepository.findByTripItemIdAndCustomTripTripId(itemId, tripId))
                .thenReturn(Optional.empty());
        
        CustomTripItem customTripItem = customTripService.retrieveCustomTripItemBytripIdAndItemId(tripId, itemId);
        
        assertNull(customTripItem);
    }
    
    // Test retrieveAllCustomTrips() method
    
    @Test
    public void testRetrieveAllCustomTrips_Success() {
        List<CustomTrip> customTrips = new ArrayList<>();
        customTrips.add(new CustomTrip());
        
        when(customTripRepository.findAll()).thenReturn(customTrips);
        
        List<CustomTrip> retrievedCustomTrips = customTripService.retrieveAllCustomTrips();
        
        assertEquals(1, retrievedCustomTrips.size());
    }
    
    @Test
    public void testRetrieveAllCustomTrips_NotFound() {
        List<CustomTrip> customTrips = new ArrayList<>();
        
        when(customTripRepository.findAll()).thenReturn(customTrips);
        
        List<CustomTrip> retrievedCustomTrips = customTripService.retrieveAllCustomTrips();
        
        assertEquals(0, retrievedCustomTrips.size());
    }
    
    // Test retrieveCustomTripBytripId() method
    
    @Test
    public void testRetrieveCustomTripBytripId_Success() {
        int tripId = 1;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.of(new CustomTrip()));
        
        CustomTrip customTrip = customTripService.retrieveCustomTripBytripId(tripId);
        
        assertNotNull(customTrip);
    }
    
    @Test
    public void testRetrieveCustomTripBytripId_NotFound() {
        int tripId = 1;
        
        when(customTripRepository.findById(tripId)).thenReturn(Optional.empty());
        
        CustomTrip customTrip = customTripService.retrieveCustomTripBytripId(tripId);
        
        assertNull(customTrip);
    }
    
    // Test retrieveAllCustomTripsByUserId() method
    
    @Test
    public void testRetrieveAllCustomTripsByUserId_Success() {
        int userId = 1;
        
        List<CustomTrip> customTrips = new ArrayList<>();
        customTrips.add(new CustomTrip());
        
        when(customTripRepository.findAllByUserId(userId)).thenReturn(customTrips);
        
        List<CustomTrip> retrievedCustomTrips = customTripService.retrieveAllCustomTripsByUserId(userId);
        
        assertEquals(1, retrievedCustomTrips.size());
    }
    
    @Test
    public void testRetrieveAllCustomTripsByUserId_NotFound() {
        int userId = 1;
        
        List<CustomTrip> customTrips = new ArrayList<>();
        
        when(customTripRepository.findAllByUserId(userId)).thenReturn(customTrips);
        
        List<CustomTrip> retrievedCustomTrips = customTripService.retrieveAllCustomTripsByUserId(userId);
        
        assertEquals(0, retrievedCustomTrips.size());
    }
    
    // Test retrieveAllCustomTripItemsBytripId() method
    
    @Test
    public void testRetrieveAllCustomTripItemsBytripId_Success() {
        int tripId = 1;
        
        List<CustomTripItem> customTripItems = new ArrayList<>();
        customTripItems.add(new CustomTripItem());
        
        when(customTripRepository.findAllCustomTripItemsBytripId(tripId)).thenReturn(customTripItems);
        
        List<CustomTripItem> retrievedCustomTripItems = customTripService.retrieveAllCustomTripItemsBytripId(tripId);
        
        assertEquals(1, retrievedCustomTripItems.size());
    }
    
    @Test
    public void testRetrieveAllCustomTripItemsBytripId_NotFound() {
        int tripId = 1;
        
        List<CustomTripItem> customTripItems = new ArrayList<>();
        
        when(customTripRepository.findAllCustomTripItemsBytripId(tripId)).thenReturn(customTripItems);
        
        List<CustomTripItem> retrievedCustomTripItems = customTripService.retrieveAllCustomTripItemsBytripId(tripId);
        
        assertEquals(0, retrievedCustomTripItems.size());
    }
}