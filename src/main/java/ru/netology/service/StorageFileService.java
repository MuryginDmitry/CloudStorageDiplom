package ru.netology.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.dto.request.FileNameEditRequest;
import ru.netology.dto.response.FileResponse;
import ru.netology.exception.InputDataException;
import ru.netology.exception.UnauthorizedException;
import ru.netology.model.StorageFile;
import ru.netology.model.User;
import ru.netology.repository.AuthenticationRepository;
import ru.netology.repository.StorageFileRepository;
import ru.netology.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StorageFileService {

    private final StorageFileRepository storageFileRepository;
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;

    public boolean uploadFile(String authToken, String filename, MultipartFile file) {
        User user = getUserByAuthToken(authToken);
        validateUser(user, "Upload file");

        try {
            storageFileRepository.save(new StorageFile(filename, LocalDateTime.now(), file.getSize(), file.getBytes(), user));
            log.info("Success upload file. User {}", user.getUsername());
            return true;
        } catch (IOException e) {
            log.error("Upload file: Input data exception");
            throw new InputDataException("Upload file: Input data exception");
        }
    }

    @Transactional
    public void deleteFile(String authToken, String filename) {
        User user = getUserByAuthToken(authToken);
        validateUser(user, "Delete file");

        storageFileRepository.deleteByUserAndFilename(user, filename);
        validateDeletedFile(storageFileRepository.findByUserAndFilename(user, filename), "Delete file");
        log.info("Success delete file. User {}", user.getUsername());
    }

    public byte[] downloadFile(String authToken, String filename) {
        User user = getUserByAuthToken(authToken);
        validateUser(user, "Download file");

        StorageFile file = storageFileRepository.findByUserAndFilename(user, filename);
        validateFile(file, "Download file");
        log.info("Success download file. User {}", user.getUsername());
        return file.getFileContent();
    }

    @Transactional
    public void editFileName(String authToken, String filename, FileNameEditRequest fileNameEditRequest) {
        User user = getUserByAuthToken(authToken);
        validateUser(user, "Edit file name");

        storageFileRepository.editFilenameByUser(user, filename, fileNameEditRequest.getFilename());
        validateDeletedFile(storageFileRepository.findByUserAndFilename(user, filename), "Edit file name");
        log.info("Success edit file name. User {}", user.getUsername());
    }

    public List<FileResponse> getAllFiles(String authToken, Integer limit) {
        User user = getUserByAuthToken(authToken);
        validateUser(user, "Get all files");

        log.info("Success get all files. User {}", user.getUsername());
        return storageFileRepository.findAllByUser(user).stream()
                .map(o -> new FileResponse(o.getFilename(), o.getSize()))
                .collect(Collectors.toList());
    }

    private User getUserByAuthToken(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            String authTokenWithoutBearer = authToken.split(" ")[1];
            String username = authenticationRepository.getUsernameByToken(authTokenWithoutBearer);
            return userRepository.findByUsername(username);
        }
        return null;
    }

    private void validateUser(User user, String action) {
        if (user == null) {
            log.error("{}: Unauthorized", action);
            throw new UnauthorizedException(action + ": Unauthorized");
        }
    }

    private void validateDeletedFile(StorageFile file, String action) {
        if (file != null) {
            log.error("{}: Input data exception", action);
            throw new InputDataException(action + ": Input data exception");
        }
    }

    private void validateFile(StorageFile file, String action) {
        if (file == null) {
            log.error("{}: Input data exception", action);
            throw new InputDataException(action + ": Input data exception");
        }
    }
}
