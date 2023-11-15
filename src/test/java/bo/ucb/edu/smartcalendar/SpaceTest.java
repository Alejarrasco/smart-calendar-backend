package bo.ucb.edu.smartcalendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bo.ucb.edu.smartcalendar.entity.Space;
import bo.ucb.edu.smartcalendar.repository.SpaceRepository;
import bo.ucb.edu.smartcalendar.bl.SpaceBl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class SpaceTest {

    @Mock
    private SpaceRepository spaceRepository;

    @InjectMocks
    private SpaceBl SpaceBl; 

    @Test
    public void testFindAll() {
        // Arrange
        Space space1 = new Space();
        space1.setSpaceName("Space 1");
        Space space2 = new Space();
        space2.setSpaceName("Space 2");
        when(spaceRepository.findAll()).thenReturn(List.of(space1, space2));

        // Act
        List<Space> spaces = SpaceBl.getAllSpaces();

        // Assert
        assertThat(spaces).hasSize(2);
        assertThat(spaces.get(0).getSpaceName()).isEqualTo("Space 1");
        assertThat(spaces.get(1).getSpaceName()).isEqualTo("Space 2");
    }

/*     @Test
    public void testFindById() {
        // Arrange
        Space space = new Space();
        space.setSpaceName("Space 1");
        space.setSpaceId(1);
        when(spaceRepository.findById(1)).thenReturn(Optional.of(space));

        // Act
        Optional<Space> foundSpace = SpaceBl.getSpaceById(1);

        // Assert
        assertThat(foundSpace).isPresent();
        assertThat(foundSpace.get().getSpaceName()).isEqualTo("Space 1");
    } */

    @Test
    public void testSaveSpace() {
        // Arrange
        Space spaceToSave = new Space();
        spaceToSave.setSpaceName("New Space");
        when(spaceRepository.save(spaceToSave)).thenReturn(spaceToSave);

        // Act
        Space savedSpace = SpaceBl.saveSpace(spaceToSave);

        // Assert
        assertThat(savedSpace.getSpaceName()).isEqualTo("New Space");
    }

}
