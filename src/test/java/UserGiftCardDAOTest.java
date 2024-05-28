import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.gamewebshop.dao.GiftCardDAO;
import com.example.gamewebshop.dao.UserDAO;
import com.example.gamewebshop.dao.UserGiftCardDAO;
import com.example.gamewebshop.dao.UserGiftCardRepository;
import com.example.gamewebshop.models.CustomUser;
import com.example.gamewebshop.models.GiftCard;
import com.example.gamewebshop.models.UserGiftCard;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserGiftCardDAOTest {

    @Mock
    private UserGiftCardRepository userGiftCardRepository;

    @Mock
    private GiftCardDAO giftCardDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserGiftCardDAO userGiftCardDAO;


    @Test
    public void testGetGiftCardsForUser() {
        // Arrange
        Long userId = 1L;
        List<UserGiftCard> sentGiftCards = new ArrayList<>();
        List<UserGiftCard> receivedGiftCards = new ArrayList<>();
        when(userGiftCardRepository.findAllBySendByIdAndActiveTrue(userId)).thenReturn(sentGiftCards);
        when(userGiftCardRepository.findAllByReceivedById(userId)).thenReturn(receivedGiftCards);

        // Act
        List<UserGiftCard> sent = userGiftCardDAO.getAllGiftCardsSentByUser(userId);
        List<UserGiftCard> received = userGiftCardDAO.getAllGiftCardsReceivedByUser(userId);

        // Assert
        assertEquals(sentGiftCards, sent);
        assertEquals(receivedGiftCards, received);
    }

    @Test
    public void testSendGiftCard() {
        // Arrange
        String email = "receiver@example.com";
        Long giftCardId = 1L;
        GiftCard giftCard = new GiftCard("Test Card", 100.0, "http://example.com/image.jpg", true);
        CustomUser sender = new CustomUser();
        CustomUser receiver = new CustomUser();
        when(userDAO.getUserByEmail("sender@example.com")).thenReturn(sender);
        when(userDAO.getUserByEmail(email)).thenReturn(receiver);
        when(giftCardDAO.getGiftCardById(giftCardId)).thenReturn(giftCard);

        // Act
        userGiftCardDAO.saveUserGiftCard(email, giftCardId);

        // Assert
        verify(userGiftCardRepository, times(1)).save(any(UserGiftCard.class));
        verify(giftCardDAO, times(1)).deleteById(giftCardId);
    }


}
