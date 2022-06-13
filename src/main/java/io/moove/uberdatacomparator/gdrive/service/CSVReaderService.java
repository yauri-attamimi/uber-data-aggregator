package io.moove.uberdatacomparator.gdrive.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CSVReaderService {

    private final TokenService tokenService;

    public List<File> listCSVFiles(String containedFileName, String mimeType, int limit) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // Build credential object.
        Credential credential = tokenService.getCredentials(HTTP_TRANSPORT);

        // Create a Google Drive Service.
        var driveService = new Drive.Builder(HTTP_TRANSPORT, TokenService.JSON_FACTORY, credential)
                .setApplicationName(TokenService.APPLICATION_NAME).build();

        FileList result = driveService.files().list()
                .setQ("mimeType='" + mimeType + "' AND name contains '" + containedFileName + "'")
                .setPageSize(limit)
                .setFields("nextPageToken, files(id, name, fileExtension, mimeType)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            log.info("No files found!");
        }
        return files;
    }
}
