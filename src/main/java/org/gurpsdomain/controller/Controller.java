package org.gurpsdomain.controller;

import org.gurpsdomain.Pipeline;
import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.input.yaml.YamlSheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.gurpsdomain.adapters.input.yaml.YamlSheetInput.sheetInputBuilder;
import static org.gurpsdomain.adapters.output.json.JsonSheetOutput.toJson;
import static org.springframework.http.ResponseEntity.status;

@RestController
public class Controller {

    @RequestMapping(method = RequestMethod.GET, value = "/{sheet:.+\\.json}")
    public ResponseEntity returnSheetText() {
        if ("marcel.json".equals("marcel.json")) {

            String sheetBody;
            sheetBody = "This is Marcels sheet!";
            return ResponseEntity.ok().header("Content-Type", "text/plain").body(sheetBody);
        } else {
            return status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/convert", produces = {"application/json"}, consumes = "application/json")
    @ResponseBody
    public String convert(@RequestBody String inputJson) {

        Reader reader = new StringReader(inputJson);
        Writer writer = new StringWriter();

        YamlSheetInput.Builder read = sheetInputBuilder("src/main/resources/data");

        SheetInput input = read.fromYaml(reader);
        SheetOutput output = toJson(writer);

        Pipeline.flow(input).into(output);

        String outputJson = writer.toString();

        return outputJson;
    }
}
