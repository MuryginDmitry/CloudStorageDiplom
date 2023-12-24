/*
package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.netology.model.StorageFile;
import ru.netology.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.TestData.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StorageFileRepositoryTest {

    @Autowired
    private StorageFileRepository storageFileRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        storageFileRepository.deleteAll();
        userRepository.save(USER_1);
        storageFileRepository.save(STORAGE_FILE_1);
        storageFileRepository.save(FOR_RENAME_STORAGE_FILE);
    }

    @Test
    void shouldDeleteByUserAndFilename() {
        Optional<StorageFile> beforeDelete = storageFileRepository.findById(FILENAME_1);
        assertTrue(beforeDelete.isPresent());
        storageFileRepository.deleteByUserAndFilename(USER_1, FILENAME_1);
        Optional<StorageFile> afterDelete = storageFileRepository.findById(FILENAME_1);
        assertFalse(afterDelete.isPresent());
    }

    @Test
    void shouldFindByUserAndFilename() {
        assertEquals(STORAGE_FILE_1, storageFileRepository.findByUserAndFilename(USER_1, FILENAME_1));
    }

    @Test
    void shouldEditFileNameByUser() {
        Optional<StorageFile> beforeEditName = storageFileRepository.findById(FOR_RENAME_FILENAME);
        assertTrue(beforeEditName.isPresent());
        assertEquals(FOR_RENAME_FILENAME, beforeEditName.get().getFilename());
        storageFileRepository.editFilenameByUser(USER_1, FOR_RENAME_FILENAME, NEW_FILENAME);
        Optional<StorageFile> afterEditName = storageFileRepository.findById(NEW_FILENAME);
        assertTrue(afterEditName.isPresent());
        assertEquals(NEW_FILENAME, afterEditName.get().getFilename());
    }

    @Test
    void shouldFindAllByUser() {
        assertEquals(List.of(STORAGE_FILE_1, FOR_RENAME_STORAGE_FILE), storageFileRepository.findAllByUser(USER_1));
    }
}
*/
