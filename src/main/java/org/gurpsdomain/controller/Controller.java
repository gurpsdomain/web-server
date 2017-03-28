package org.gurpsdomain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
public class Controller {

    @RequestMapping(method = RequestMethod.GET, value = "/{sheet:.+\\.json}")
    public ResponseEntity returnSheet() {
        if ("marcel.json".equals("marcel.json")) {

            String sheetBody;
            sheetBody = "This is Marcels sheet!";
            return ResponseEntity.ok().header("Content-Type", "text/plain").body(sheetBody);
        } else {
            return status(HttpStatus.NOT_FOUND).build();
        }
    }
}
