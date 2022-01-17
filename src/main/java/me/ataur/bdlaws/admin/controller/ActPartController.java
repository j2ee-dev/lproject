package me.ataur.bdlaws.admin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import me.ataur.bdlaws.admin.model.ActPart;
import me.ataur.bdlaws.admin.model.PartNameFootnoteForActPart;
import me.ataur.bdlaws.admin.model.PartNoFootnoteForActPart;
import me.ataur.bdlaws.admin.repository.*;
import me.ataur.bdlaws.admin.validator.ActPartValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/ActPart", "/admin/actPart"})
public class ActPartController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(ActPartController.class);
    @Autowired
    ActPartRepository actPartRepository;
    @Autowired
    ActPartDataTableRepository actPartDataTableRepository;
    @Autowired
    VolumeRepository volumeRepository;
    @Autowired
    ActRepository actRepository;
    @Autowired
    ActPartValidator actPartValidator;
    @Autowired
    PartNameFootnoteForActPartRepository partNameFootnoteForActPartRepository;
    @Autowired
    PartNoFootnoteForActPartRepository partNoFootnoteForActPartRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/ActPart/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        ActPart actPart = new ActPart();
        model.addAttribute("actPart", actPart);
        model.addAttribute("action", "/create");
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        model.addAttribute("actList", null);
        return "admin/pages/ActPart/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("actPart") ActPart actPart, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {


        String attachment = uploadFile(attachmentFile);
        actPart.setAttachment(null);
        if (attachment == null) {
            actPart.setAttachment(null);
        } else {
            actPart.setAttachment(attachment);
        }


        actPartValidator.validate(actPart, result);
        if (result.hasErrors()) {
            model.addAttribute("actPart", actPart);
            model.addAttribute("action", "/create");

            model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
            model.addAttribute("actList", null);
            return "admin/pages/ActPart/create";
        }


        if (actPart.getPartNoFootnoteList() != null) {
            for (PartNoFootnoteForActPart partNoFootnote : actPart.getPartNoFootnoteList()
                    ) {

                if (partNoFootnote.getActPart() == null) {
                    partNoFootnote.setActPart(actPart);
                }
                partNoFootnote.setCreatedBy(getUsername());
                partNoFootnote.setCreatedAt(new Date());

            }
        }

        if (actPart.getPartNameFootnoteList() != null) {
            for (PartNameFootnoteForActPart partNameFootnote : actPart.getPartNameFootnoteList()
                    ) {

                if (partNameFootnote.getActPart() == null) {
                    partNameFootnote.setActPart(actPart);
                }
                partNameFootnote.setCreatedBy(getUsername());
                partNameFootnote.setCreatedAt(new Date());
            }
        }


        if (actPart.getVolume().getId() == 0) {
            actPart.setVolume(null);
        }
        if (actPart.getActId().getId() == 0) {
            actPart.setActId(null);
        }
        actPart.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actPart.setCreatedAt(new Date());
        actPartRepository.save(actPart);
        model.addAttribute("actPart", actPartRepository.getNewActPart());
        return "admin/pages/ActPart/details";
        //return "redirect:/admin/actPart";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {


        ActPart actPart = actPartRepository.findOne(id);
        model.addAttribute("actPart", actPart);
        model.addAttribute("action", "/edit/" + id);
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        model.addAttribute("actList", null);
        return "admin/pages/ActPart/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("actPart") ActPart actPart, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {

        ActPart actPartOld = actPartRepository.findOne(id);
        String attachment = uploadFile(attachmentFile);
        actPart.setAttachment(null);
        if (attachment == null) {
            actPart.setAttachment(actPartOld.getAttachment());
        } else {
            actPart.setAttachment(attachment);
        }

        actPartValidator.validate(actPart, result);
        if (result.hasErrors()) {
            model.addAttribute("actPart", actPart);
            model.addAttribute("action", "/edit/" + id);
            model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
            model.addAttribute("actList", null);
            return "admin/pages/ActPart/edit";
        }


        if (actPart.getPartNoFootnoteList() != null) {
            Integer tracker = 1;
            for (PartNoFootnoteForActPart partNoFootnote : actPart.getPartNoFootnoteList()
                    ) {

                for (Integer i = partNoFootnote.getFootnoteNumber() + 1; i <= actPart.getPartNoFootnoteList().size(); i++) {
                    List<PartNoFootnoteForActPart> FN = actPart.getPartNoFootnoteList();
                    Integer dupfnumber;
                    PartNoFootnoteForActPart oneFN = FN.get(actPart.getPartNoFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == actPart.getPartNoFootnoteList().size()) {

                        if (partNoFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = partNoFootnote.getFootnoteNumber();

                            List<PartNoFootnoteForActPart> footnotes = partNoFootnoteForActPartRepository.getFootNoatFrom(dupfnumber, actPart.getId());
                            PartNoFootnoteForActPart footnotesOld = partNoFootnoteForActPartRepository.getOneFootNoat(dupfnumber, actPart.getId());
                            actPart.getPartNoFootnoteList().get(actPart.getPartNoFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer nolastfnid= partNoFootnoteForActPartRepository.getLastId()+1;
                            for (PartNoFootnoteForActPart Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    actPart.getPartNoFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    actPart.getPartNoFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(nolastfnid);
                                    nolastfnid++;
                                }

                            }


                        }

                    }

                }
                if (tracker < actPart.getPartNoFootnoteList().size()) {
                    tracker = tracker + 1;
                }

                if (partNoFootnote.getActPart() == null) {
                    partNoFootnote.setActPart(actPart);
                }
                partNoFootnote.setCreatedBy(actPartOld.getCreatedBy());
                partNoFootnote.setCreatedAt(actPartOld.getCreatedAt());
                partNoFootnote.setUpdatedBy(getUsername());
                partNoFootnote.setUpdatedAt(new Date());
            }
        }

        if (actPart.getPartNameFootnoteList() != null) {
            Integer tracker = 1;
            for (PartNameFootnoteForActPart partNameFootnote : actPart.getPartNameFootnoteList()
                    ) {

                for (Integer i = partNameFootnote.getFootnoteNumber() + 1; i <= actPart.getPartNameFootnoteList().size(); i++) {
                    List<PartNameFootnoteForActPart> FN = actPart.getPartNameFootnoteList();
                    Integer dupfnumber;
                    PartNameFootnoteForActPart oneFN = FN.get(actPart.getPartNameFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == actPart.getPartNameFootnoteList().size()) {

                        if (partNameFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = partNameFootnote.getFootnoteNumber();

                            List<PartNameFootnoteForActPart> footnotes = partNameFootnoteForActPartRepository.getFootNoatFrom(dupfnumber, actPart.getId());
                            PartNameFootnoteForActPart footnotesOld = partNameFootnoteForActPartRepository.getOneFootNoat(dupfnumber, actPart.getId());
                            actPart.getPartNameFootnoteList().get(actPart.getPartNameFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer nlastfnid= partNameFootnoteForActPartRepository.getLastId()+1;
                            for (PartNameFootnoteForActPart Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    actPart.getPartNameFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    actPart.getPartNameFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(nlastfnid);
                                    nlastfnid++;
                                }
                            }
                        }

                    }

                }
                if (tracker < actPart.getPartNameFootnoteList().size()) {
                    tracker = tracker + 1;
                }

                if (partNameFootnote.getActPart() == null) {
                    partNameFootnote.setActPart(actPart);
                }
                partNameFootnote.setCreatedBy(actPartOld.getCreatedBy());
                partNameFootnote.setCreatedAt(actPartOld.getCreatedAt());
                partNameFootnote.setUpdatedBy(getUsername());
                partNameFootnote.setUpdatedAt(new Date());
            }
        }


        if (actPart.getVolume().getId() == 0) {
            actPart.setVolume(null);
        }
        if (actPart.getActId().getId() == 0) {
            actPart.setActId(null);
        }
        actPart.setCreatedBy(actPartOld.getCreatedBy());
        actPart.setCreatedAt(actPartOld.getCreatedAt());

        actPart.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actPart.setUpdatedAt(new Date());

        actPartRepository.save(actPart);


        try {
            SimpleDateFormat sdf=new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
            SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

            if(partNoFootnoteForActPartRepository.checkingDuplicate(id)!=null)
            {
                PartNoFootnoteForActPart partNoFootnoteForActPartAgain= partNoFootnoteForActPartRepository.findFirst(id);
                String dateAgain =partNoFootnoteForActPartAgain.getUpdatedAt().toString();
                //System.out.println("AGAIN DATE IS"+dateAgain);
                String fromd = dateAgain;
                // System.out.println(fromd);
                Date fd = sdf.parse(fromd);
                //System.out.println(sdf2.format(fd));
                partNoFootnoteForActPartRepository.deleteDuplicateFootnote(sdf2.format(fd),id);

            }
            if(partNameFootnoteForActPartRepository.checkingDuplicate(id)!=null)
            {
                PartNameFootnoteForActPart partNameFootnoteForActPartAgain= partNameFootnoteForActPartRepository.findFirst(id);
                String dateNameAgain =partNameFootnoteForActPartAgain.getUpdatedAt().toString();
                //System.out.println("AGAIN DATE IS"+dateNameAgain);
                String fromdName = dateNameAgain;
                Date fdName = sdf.parse(fromdName);
                partNameFootnoteForActPartRepository.deleteDuplicateFootnote(sdf2.format(fdName),id);
            }
        }
        catch (ParseException e) {
            System.out.println("Parse caught");
        }
        catch(NullPointerException e)
        {
            System.out.println("IllegalArgumentException caught");
        }


        return "redirect:/admin/actPart";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        ActPart actPart = actPartRepository.findOne(id);
        model.addAttribute("actPart", actPart);
        return "admin/pages/ActPart/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        actPartRepository.delete(id);
        Map<String, Object> response = new HashMap();

        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ActPart getById(@PathVariable("id") Integer id, Model model) {
        ActPart actPart = actPartRepository.findOne(id);
        return actPart;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<ActPart> data() {
        return actPartRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<ActPart> getActParts(@Valid @RequestBody DataTablesInput input) {

        return actPartDataTableRepository.findAll(input);
    }


    @RequestMapping(value = "/act-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getActDropDownListByvolume(@RequestParam("volume.id") Integer volume) {
        return actRepository.getActDropDownListByvolume(volume);
    }

}
