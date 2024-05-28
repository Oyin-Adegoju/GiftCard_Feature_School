

import com.example.gamewebshop.dao.GiftCardDAO;
import com.example.gamewebshop.dao.GiftCardRepository;
import com.example.gamewebshop.models.GiftCard;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GiftCardDAOTest {

    @Mock
    private GiftCardRepository giftCardRepository;

    @InjectMocks
    private GiftCardDAO giftCardDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllActiveGiftCards() {
        // Arrange
        GiftCard giftCard1 = new GiftCard();
        giftCard1.setId(1L);
        giftCard1.setAmount(25.0);
        giftCard1.setCardCode("GC1234567890");
        giftCard1.setActive(true);

        GiftCard giftCard2 = new GiftCard();
        giftCard2.setId(2L);
        giftCard2.setAmount(50.0);
        giftCard2.setCardCode("GC0987654321");
        giftCard2.setActive(true);

        List<GiftCard> expectedGiftCards = Arrays.asList(giftCard1, giftCard2);

        when(giftCardRepository.findAllByActiveTrue()).thenReturn(expectedGiftCards);

        // Act
        List<GiftCard> result = giftCardDAO.getAllActiveGiftCards();

        // Assert
        assertEquals(expectedGiftCards.size(), result.size());
        assertEquals(expectedGiftCards, result);
    }

    @Test
    public void testCreateGiftCard() {
        // Arrange
        GiftCard giftCard = new GiftCard("Test Card", 100.0, "http://example.com/image.jpg", true);

        // Act
        giftCardDAO.createGiftCard(giftCard);

        // Assert
        verify(giftCardRepository, times(1)).save(giftCard);
        assertNotNull(giftCard.getCardCode());
    }

    @Test
    public void testDeleteGiftCard() {
        // Arrange
        Long giftCardId = 1L;
        GiftCard giftCard = new GiftCard("Delete Me", 100.0, "http://example.com/image.jpg", true);
        when(giftCardRepository.findByIdAndActiveTrue(giftCardId)).thenReturn(Optional.of(giftCard));

        // Act
        giftCardDAO.deleteById(giftCardId);

        // Assert
        verify(giftCardRepository, times(1)).save(giftCard);
        assertFalse(giftCard.isActive());
    }



    // Add more tests for other methods if needed
}
