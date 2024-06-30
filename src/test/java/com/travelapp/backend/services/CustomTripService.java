import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.travelapp.backend.models.CustomTrip;
import com.travelapp.backend.models.CustomTripItem;
import com.travelapp.backend.repositories.CustomTripItemRepository;
import com.travelapp.backend.repositories.CustomTripRepository;
import com.travelapp.backend.services.CustomTripService;

public class CustomTripServiceTests {

    @Mock
    private CustomTripRepository customTripRepository;

    @Mock
    private CustomTripItemRepository customTripItemRepository;

    @InjectMocks
    private CustomTripService customTripService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateCustomTripByTripId() {
        // Mock data
        CustomTrip existingTrip = new CustomTrip();
        existingTrip.setTripId(1);
        existingTrip.setTripName("Trip One");

        CustomTripItem item1 = new CustomTripItem();
        item1.setItemId(1);
        item1.setItemName("Item 1");

        List<CustomTripItem> existingItems = new ArrayList<>();
        existingItems.add(item1);
        existingTrip.setCustomTripItems(existingItems);

        CustomTrip updatedTrip = new CustomTrip();
        updatedTrip.setTripId(1);
        updatedTrip.setTripName("Updated Trip");

        CustomTripItem newItem = new CustomTripItem();
        newItem.setItemId(2);
        newItem.setItemName("New Item");

        List<CustomTripItem> updatedItems = new ArrayList<>();
        updatedItems.add(item1);
        updatedItems.add(newItem);
        updatedTrip.setCustomTripItems(updatedItems);

        // Mock repository behavior
        when(customTripRepository.findById(1)).thenReturn(Optional.of(existingTrip));
        when(customTripItemRepository.findAllByCustomTripTripId(1)).thenReturn(existingItems);

        // Call the method
        CustomTrip result = customTripService.updateCustomTripByTripId(1, updatedTrip);

        // Verify repository interactions
        ArgumentCaptor<CustomTrip> tripCaptor = ArgumentCaptor.forClass(CustomTrip.class);
        verify(customTripRepository).save(tripCaptor.capture());
        CustomTrip savedTrip = tripCaptor.getValue();

        // Assertions
        assertEquals("Updated Trip", savedTrip.getTripName());
        assertEquals(2, savedTrip.getCustomTripItems().size());
        assertTrue(savedTrip.getCustomTripItems().contains(item1));
        assertTrue(savedTrip.getCustomTripItems().contains(newItem));
    }
}
