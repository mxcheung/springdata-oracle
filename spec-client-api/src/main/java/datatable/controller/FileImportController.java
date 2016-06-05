/**
 * 
 */
package datatable.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ledwinson
 *
 */
@Controller
@RequestMapping("/validation")
public interface FileImportController {
    
    /**
     * If the file has at-least one rule set compilation errors, then no rules are saved, the error will be returned to the caller. 
     * @param file
     * @param dataCustodian
     * @return The file import result DTO.
     */
    @RequestMapping(value="/importrules/{dataCustodian}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    String importData(@RequestPart("rulefile") MultipartFile file, @PathVariable("dataCustodian") String dataCustodian);
    
    /**
     * We have a different end point to upload Auth rules as we don't keep the file history for auth rules.
     * <br/>
     * Why there is a different mechanism? 
     * <br/>
     * Because users find it very tricky with AUTH roles and permissions. So they want to change roles and test it in UAT then import the same to PROD.
     * <br/>
     * So the export auth rules always pick up the rules from database [because this can be changed via GUI as well] and generate file.
     * <br/>
     * And the import auth rules won't save the file as there is no use.
     *  
     * @param file
     * @param dataCustodian
     * @return
     */
    @RequestMapping(value="/importrules/auth/{dataCustodian}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    Boolean importAuthData(@RequestPart("rulefile") MultipartFile file, @PathVariable("dataCustodian") String dataCustodian);
    
    @RequestMapping(value="/{filename}/{custodian}/acceptable", method = RequestMethod.GET)
    ResponseEntity<Boolean> isFilenameUnique(@PathVariable("filename") String fileName, @PathVariable("custodian") String dataCustodian);
        
    @RequestMapping(value="/rulefile/{custodian}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody ResponseEntity<byte[]> getRuleFile(@PathVariable("custodian") String dataCustodian);
    

    @RequestMapping(value="/updateRuleStatus/{dataCustodian}", method = RequestMethod.POST)
    @ResponseBody void updateRuleIdentifierStatus(@PathVariable("custodian") String dataCustodian);
    
}
