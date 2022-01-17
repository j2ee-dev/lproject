/*
package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.*;

import me.ataur.bdlaws.admin.repository.*;


import me.ataur.bdlaws.admin.validator.ActSectionValidator;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

*/
/**
 * Created by imran hossain
 *//*


@Controller
@RequestMapping({"/admin/ActSection", "/admin/actSection"})
public class ActSectionController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(ActSectionController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ActSectionRepository actSectionRepository;
    @Autowired
    ActSectionDataTableRepository actSectionDataTableRepository;


    @Autowired
    VolumeRepository volumeRepository;

    @Autowired
    ActRepository actRepository;

    @Autowired
    ActPartRepository actPartRepository;

    @Autowired
    ActChapterRepository actChapterRepository;

    @Autowired
    ActSectionValidator actSectionValidator;
    @Autowired
    SectionDescriptionFootnoteActSectionRepository sectionDescriptionFootnoteActSectionRepository;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/ActSection/datatable";
    }

    @RequestMapping(value = {"/create", "/create*/
/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        ActSection actSection = new ActSection();
        model.addAttribute("actSection", actSection);
        model.addAttribute("action", "/create");
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        model.addAttribute("actList", null);
        model.addAttribute("actPartList", null);
        model.addAttribute("actChapterList", null);
        return "admin/pages/ActSection/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("actSection") ActSection actSection, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {


        String attachment = uploadFile(attachmentFile);
        actSection.setAttachment(null);
        if (attachment == null) {
            actSection.setAttachment(null);
        } else {
            actSection.setAttachment(attachment);
        }


        actSectionValidator.validate(actSection, result);
        if (result.hasErrors()) {
            model.addAttribute("actSection", actSection);
            model.addAttribute("action", "/create");

            model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());

            model.addAttribute("actList", null);

            model.addAttribute("actPartList", null);

            model.addAttribute("actChapterList", null);

            return "admin/pages/ActSection/create";
        }


        if (actSection.getSectionNameFootnoteList() != null) {
            for (SectionNameFootnoteForActSection sectionNameFootnote : actSection.getSectionNameFootnoteList()
                    ) {
                if (sectionNameFootnote.getActSection() == null) {
                    sectionNameFootnote.setActSection(actSection);
                }
                sectionNameFootnote.setCreatedBy(getUsername());
                sectionNameFootnote.setCreatedAt(new Date());

            }
        }

        if (actSection.getSectionHeadFootnoteList() != null) {
            for (SectionHeadFootnoteForActSection sectionHeadFootnote : actSection.getSectionHeadFootnoteList()
                    ) {
                if (sectionHeadFootnote.getActSection() == null) {
                    sectionHeadFootnote.setActSection(actSection);
                }
                sectionHeadFootnote.setCreatedBy(getUsername());
                sectionHeadFootnote.setCreatedAt(new Date());
            }
        }


        if (actSection.getSectionDescriptionFootnoteList() != null) {
            for (SectionDescriptionFootnoteForActSection sectionDescriptionFootnote : actSection.getSectionDescriptionFootnoteList()
                    ) {
                if (sectionDescriptionFootnote.getActSection() == null) {
                    sectionDescriptionFootnote.setActSection(actSection);
                }
                sectionDescriptionFootnote.setCreatedBy(getUsername());
                sectionDescriptionFootnote.setCreatedAt(new Date());
            }
        }


        if (actSection.getSectionTableFootnoteList() != null) {
            for (SectionTableFootnoteForActSection sectionTableFootnote : actSection.getSectionTableFootnoteList()
                    ) {
                if (sectionTableFootnote.getActSection() == null) {
                    sectionTableFootnote.setActSection(actSection);
                }
                sectionTableFootnote.setCreatedBy(getUsername());
                sectionTableFootnote.setCreatedAt(new Date());
            }
        }


        if (actSection.getVolume().getId() == 0) {
            actSection.setVolume(null);
        }
        if (actSection.getActId().getId() == 0) {
            actSection.setActId(null);
        }
        if (actSection.getPartId().getId() == 0) {
            actSection.setPartId(null);
        }
        if (actSection.getChapterId().getId() == 0) {
            actSection.setChapterId(null);
        }
        actSection.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actSection.setCreatedAt(new Date());
        actSectionRepository.save(actSection);

        model.addAttribute("actSection",actSectionRepository.getNewActSection());

        return "/admin/pages/ActSection/details";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        ActSection actSection = actSectionRepository.findOne(id);
        model.addAttribute("actSection", actSection);
        model.addAttribute("action", "/edit/" + id);
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        model.addAttribute("actList", null);
        model.addAttribute("actPartList", null);
        model.addAttribute("actChapterList", null);
        return "admin/pages/ActSection/create";
    }




    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("actSection") ActSection actSection, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {

        ActSection actSectionOld = actSectionRepository.findOne(id);
        String attachment = uploadFile(attachmentFile);
        actSection.setAttachment(null);
        if (attachment == null) {
            actSection.setAttachment(actSectionOld.getAttachment());
        } else {
            actSection.setAttachment(attachment);
        }

        actSectionValidator.validate(actSection, result);
        if (result.hasErrors()) {
            model.addAttribute("actSection", actSection);
            model.addAttribute("action", "/edit/" + id);
            model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
            model.addAttribute("actList", null);
            model.addAttribute("actPartList", null);
            model.addAttribute("actChapterList", null);
            return "admin/pages/ActSection/create";
        }


        if (actSection.getSectionNameFootnoteList() != null) {
            for (SectionNameFootnoteForActSection sectionNameFootnote : actSection.getSectionNameFootnoteList()
                    ) {
                if (sectionNameFootnote.getActSection() == null) {
                    sectionNameFootnote.setActSection(actSection);
                }
                sectionNameFootnote.setCreatedBy(actSectionOld.getCreatedBy());
                sectionNameFootnote.setCreatedAt(actSectionOld.getCreatedAt());
                sectionNameFootnote.setUpdatedBy(getUsername());
                sectionNameFootnote.setUpdatedAt(new Date());
            }
        }


        if (actSection.getSectionHeadFootnoteList() != null) {
            for (SectionHeadFootnoteForActSection sectionHeadFootnote : actSection.getSectionHeadFootnoteList()
                    ) {
                if (sectionHeadFootnote.getActSection() == null) {
                    sectionHeadFootnote.setActSection(actSection);
                }
                sectionHeadFootnote.setCreatedBy(actSectionOld.getCreatedBy());
                sectionHeadFootnote.setCreatedAt(actSectionOld.getCreatedAt());
                sectionHeadFootnote.setUpdatedBy(getUsername());
                sectionHeadFootnote.setUpdatedAt(new Date());
            }
        }


        if (actSection.getSectionDescriptionFootnoteList() != null) {

            Integer tracker = 1;

            for (SectionDescriptionFootnoteForActSection sectionDescriptionFootnote : actSection.getSectionDescriptionFootnoteList()
                    ) {
                for (Integer i = sectionDescriptionFootnote.getFootnoteNumber() + 1; i <= actSection.getSectionDescriptionFootnoteList().size(); i++) {
                    List<SectionDescriptionFootnoteForActSection> FN = actSection.getSectionDescriptionFootnoteList();
                    Integer dupfnumber;
                    SectionDescriptionFootnoteForActSection oneFN = FN.get(actSection.getSectionDescriptionFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == actSection.getSectionDescriptionFootnoteList().size()) {

                        if (sectionDescriptionFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = sectionDescriptionFootnote.getFootnoteNumber();

                            List<SectionDescriptionFootnoteForActSection> footnotes = sectionDescriptionFootnoteActSectionRepository.getFootNoatFrom(dupfnumber, actSection.getId());
                            SectionDescriptionFootnoteForActSection footnotesOld = sectionDescriptionFootnoteActSectionRepository.getOneFootNoat(dupfnumber, actSection.getId());
                            actSection.getSectionDescriptionFootnoteList().get(actSection.getSectionDescriptionFootnoteList().size() - 1).setId(footnotesOld.getId());

                            for (SectionDescriptionFootnoteForActSection Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack < footnotes.size()) {
                                    actSection.getSectionDescriptionFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    actSection.getSectionDescriptionFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(Footnote.getId() + 1);

                                }

                            }


                        }

                    }

                }
                if (tracker < actSection.getSectionDescriptionFootnoteList().size()) {
                    tracker = tracker + 1;
                }


                if (sectionDescriptionFootnote.getActSection() == null) {
                    sectionDescriptionFootnote.setActSection(actSection);
                }
                sectionDescriptionFootnote.setCreatedBy(actSectionOld.getCreatedBy());
                sectionDescriptionFootnote.setCreatedAt(actSectionOld.getCreatedAt());
                sectionDescriptionFootnote.setUpdatedBy(getUsername());
                sectionDescriptionFootnote.setUpdatedAt(new Date());

        }

        }


        if (actSection.getSectionTableFootnoteList() != null) {
            for (SectionTableFootnoteForActSection sectionTableFootnote : actSection.getSectionTableFootnoteList()
                    ) {
                if (sectionTableFootnote.getActSection() == null) {
                    sectionTableFootnote.setActSection(actSection);
                }

                sectionTableFootnote.setCreatedBy(actSectionOld.getCreatedBy());
                sectionTableFootnote.setCreatedAt(actSectionOld.getCreatedAt());
                sectionTableFootnote.setUpdatedBy(getUsername());
                sectionTableFootnote.setUpdatedAt(new Date());
            }
        }


        if (actSection.getVolume().getId() == 0) {
            actSection.setVolume(null);
        }
        if (actSection.getActId().getId() == 0) {
            actSection.setActId(null);
        }
        if (actSection.getPartId().getId() == 0) {
            actSection.setPartId(null);
        }
        if (actSection.getChapterId().getId() == 0) {
            actSection.setChapterId(null);
        }
        actSection.setCreatedBy(actSectionOld.getCreatedBy());
        actSection.setCreatedAt(actSectionOld.getCreatedAt());
        actSection.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actSection.setUpdatedAt(new Date());
        actSectionRepository.save(actSection);
        return "redirect:/admin/actSection";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        ActSection actSection = actSectionRepository.findOne(id);
        model.addAttribute("actSection", actSection);
        return "admin/pages/ActSection/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        actSectionRepository.delete(id);
        Map<String, Object> response = new HashMap();
        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ActSection getById(@PathVariable("id") Integer id, Model model) {
        ActSection actSection = actSectionRepository.findOne(id);
        return actSection;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<ActSection> data() {
        return actSectionRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<ActSection> getActSections(@Valid @RequestBody DataTablesInput input) {

        return actSectionDataTableRepository.findAll(input);
    }


    @RequestMapping(value = "/act-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getActDropDownListByvolume(@RequestParam("volume.id") Integer volume) {
        return actRepository.getActDropDownListByvolume(volume);
    }


    @RequestMapping(value = "/actPart-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getActPartDropDownListByActId(@RequestParam("actId.id") Integer actId) {
        return actPartRepository.getActPartDropDownListByActId(actId);
    }


    @RequestMapping(value = "/actChapter-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getActChapterDropDownListByPartId(@RequestParam(value = "actId.id") Integer actId, @RequestParam("partId.id") Integer partId) {
        //logger.info("PartId = "+partId.toString());
        //logger.info("ActId = "+actId.toString());
        if (partId == 0) {
            return actChapterRepository.getActChapterDropDownListByActIdAndPartIdIsNull(actId);
        } else {
            return actChapterRepository.getActChapterDropDownListByPartId(partId);
        }

    }

}
*/


package me.ataur.bdlaws.admin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import me.ataur.bdlaws.admin.model.*;
import me.ataur.bdlaws.admin.repository.*;
import me.ataur.bdlaws.admin.validator.ActSectionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/ActSection", "/admin/actSection"})
public class ActSectionController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(ActSectionController.class);
    @Autowired
    ActSectionRepository actSectionRepository;
    @Autowired
    ActSectionDataTableRepository actSectionDataTableRepository;
    @Autowired
    VolumeRepository volumeRepository;
    @Autowired
    ActRepository actRepository;
    @Autowired
    ActPartRepository actPartRepository;
    @Autowired
    ActChapterRepository actChapterRepository;
    @Autowired
    ActSectionValidator actSectionValidator;
    @Autowired
    SectionDescriptionFootnoteActSectionRepository sectionDescriptionFootnoteActSectionRepository;
    @Autowired
    SectionHeadFootnoteActSectionRepository sectionHeadFootnoteActSectionRepository;
    @Autowired
    SectionNameFootnoteActSectionRepository sectionNameFootnoteActSectionRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/ActSection/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        ActSection actSection = new ActSection();
        model.addAttribute("actSection", actSection);
        model.addAttribute("action", "/create");
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        model.addAttribute("actList", null);
        model.addAttribute("actPartList", null);
        model.addAttribute("actChapterList", null);
        return "admin/pages/ActSection/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("actSection") ActSection actSection, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {


        String attachment = uploadFile(attachmentFile);
        actSection.setAttachment(null);
        if (attachment == null) {
            actSection.setAttachment(null);
        } else {
            actSection.setAttachment(attachment);
        }


        actSectionValidator.validate(actSection, result);
        if (result.hasErrors()) {
            model.addAttribute("actSection", actSection);
            model.addAttribute("action", "/create");

            model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());

            model.addAttribute("actList", null);

            model.addAttribute("actPartList", null);

            model.addAttribute("actChapterList", null);

            return "admin/pages/ActSection/create";
        }


        if (actSection.getSectionNameFootnoteList() != null) {
            for (SectionNameFootnoteForActSection sectionNameFootnote : actSection.getSectionNameFootnoteList()
                    ) {
                if (sectionNameFootnote.getActSection() == null) {
                    sectionNameFootnote.setActSection(actSection);
                }
                sectionNameFootnote.setCreatedBy(getUsername());
                sectionNameFootnote.setCreatedAt(new Date());

            }
        }

        if (actSection.getSectionHeadFootnoteList() != null) {
            for (SectionHeadFootnoteForActSection sectionHeadFootnote : actSection.getSectionHeadFootnoteList()
                    ) {
                if (sectionHeadFootnote.getActSection() == null) {
                    sectionHeadFootnote.setActSection(actSection);
                }
                sectionHeadFootnote.setCreatedBy(getUsername());
                sectionHeadFootnote.setCreatedAt(new Date());
            }
        }


        if (actSection.getSectionDescriptionFootnoteList() != null) {
            for (SectionDescriptionFootnoteForActSection sectionDescriptionFootnote : actSection.getSectionDescriptionFootnoteList()
                    ) {
                if (sectionDescriptionFootnote.getActSection() == null) {
                    sectionDescriptionFootnote.setActSection(actSection);
                }
                sectionDescriptionFootnote.setCreatedBy(getUsername());
                sectionDescriptionFootnote.setCreatedAt(new Date());
            }
        }


        if (actSection.getSectionTableFootnoteList() != null) {
            for (SectionTableFootnoteForActSection sectionTableFootnote : actSection.getSectionTableFootnoteList()
                    ) {
                if (sectionTableFootnote.getActSection() == null) {
                    sectionTableFootnote.setActSection(actSection);
                }
                sectionTableFootnote.setCreatedBy(getUsername());
                sectionTableFootnote.setCreatedAt(new Date());
            }
        }


        if (actSection.getVolume().getId() == 0) {
            actSection.setVolume(null);
        }
        if (actSection.getActId().getId() == 0) {
            actSection.setActId(null);
        }
        if (actSection.getPartId().getId() == 0) {
            actSection.setPartId(null);
        }
        if (actSection.getChapterId().getId() == 0) {
            actSection.setChapterId(null);
        }
        actSection.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actSection.setCreatedAt(new Date());
        actSectionRepository.save(actSection);

        model.addAttribute("actSection", actSectionRepository.getNewActSection());

        return "/admin/pages/ActSection/details";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        ActSection actSection = actSectionRepository.findOne(id);
        model.addAttribute("actSection", actSection);
        model.addAttribute("action", "/edit/" + id);
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        model.addAttribute("actPart",actPartRepository.findAllByOrderByIdDesc());
        model.addAttribute("actChapter",actChapterRepository.findAllByOrderByIdDesc());
        model.addAttribute("actList", null);
        //model.addAttribute("actPartList", null);
        //model.addAttribute("actChapterList", null);
        return "admin/pages/ActSection/edit";
    }

    @Transactional
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("actSection") ActSection actSection, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {

        ActSection actSectionOld = actSectionRepository.findOne(id);
        String attachment = uploadFile(attachmentFile);
        actSection.setAttachment(null);
        if (attachment == null) {
            actSection.setAttachment(actSectionOld.getAttachment());
        } else {
            actSection.setAttachment(attachment);
        }

        actSectionValidator.validate(actSection, result);
        if (result.hasErrors()) {
            model.addAttribute("actSection", actSection);
            model.addAttribute("action", "/edit/" + id);
            model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
            model.addAttribute("actList", null);
            model.addAttribute("actPartList", null);
            model.addAttribute("actChapterList", null);
            return "admin/pages/ActSection/edit";
        }


        if (actSection.getSectionNameFootnoteList() != null) {
            Integer tracker = 1;
            for (SectionNameFootnoteForActSection sectionNameFootnote : actSection.getSectionNameFootnoteList()
                    ) {

                for (Integer i = sectionNameFootnote.getFootnoteNumber() + 1; i <= actSection.getSectionNameFootnoteList().size(); i++) {
                    List<SectionNameFootnoteForActSection> FN = actSection.getSectionNameFootnoteList();
                    Integer dupfnumber;
                    SectionNameFootnoteForActSection oneFN = FN.get(actSection.getSectionNameFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == actSection.getSectionNameFootnoteList().size()) {

                        if (sectionNameFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = sectionNameFootnote.getFootnoteNumber();

                            List<SectionNameFootnoteForActSection> footnotes = sectionNameFootnoteActSectionRepository.getFootNoatFrom(dupfnumber, actSection.getId());
                            SectionNameFootnoteForActSection footnotesOld = sectionNameFootnoteActSectionRepository.getOneFootNoat(dupfnumber, actSection.getId());
                            actSection.getSectionNameFootnoteList().get(actSection.getSectionNameFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer nlastfnid= sectionNameFootnoteActSectionRepository.getLastId()+1;
                            for (SectionNameFootnoteForActSection Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    actSection.getSectionNameFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    actSection.getSectionNameFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(nlastfnid);
                                    nlastfnid++;
                                }

                            }


                        }

                    }

                }
                if (tracker < actSection.getSectionNameFootnoteList().size()) {
                    tracker = tracker + 1;
                }
                if (sectionNameFootnote.getActSection() == null) {
                    sectionNameFootnote.setActSection(actSection);
                }
                sectionNameFootnote.setCreatedBy(actSectionOld.getCreatedBy());
                sectionNameFootnote.setCreatedAt(actSectionOld.getCreatedAt());
                sectionNameFootnote.setUpdatedBy(getUsername());
                sectionNameFootnote.setUpdatedAt(new Date());
            }
        }


        if (actSection.getSectionHeadFootnoteList() != null) {
            Integer tracker = 1;
            for (SectionHeadFootnoteForActSection sectionHeadFootnote : actSection.getSectionHeadFootnoteList()
                    ) {

                for (Integer i = sectionHeadFootnote.getFootnoteNumber() + 1; i <= actSection.getSectionHeadFootnoteList().size(); i++) {
                    List<SectionHeadFootnoteForActSection> FN = actSection.getSectionHeadFootnoteList();
                    Integer dupfnumber;
                    SectionHeadFootnoteForActSection oneFN = FN.get(actSection.getSectionHeadFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == actSection.getSectionHeadFootnoteList().size()) {

                        if (sectionHeadFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = sectionHeadFootnote.getFootnoteNumber();

                            List<SectionHeadFootnoteForActSection> footnotes = sectionHeadFootnoteActSectionRepository.getFootNoatFrom(dupfnumber, actSection.getId());
                            SectionHeadFootnoteForActSection footnotesOld = sectionHeadFootnoteActSectionRepository.getOneFootNoat(dupfnumber, actSection.getId());
                            actSection.getSectionHeadFootnoteList().get(actSection.getSectionHeadFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer hlastfnid= sectionHeadFootnoteActSectionRepository.getLastId()+1;

                            for (SectionHeadFootnoteForActSection Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    actSection.getSectionHeadFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    actSection.getSectionHeadFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(hlastfnid);
                                    hlastfnid++;

                                }

                            }


                        }

                    }

                }
                if (tracker < actSection.getSectionHeadFootnoteList().size()) {
                    tracker = tracker + 1;
                }
                if (sectionHeadFootnote.getActSection() == null) {
                    sectionHeadFootnote.setActSection(actSection);
                }
                sectionHeadFootnote.setCreatedBy(actSectionOld.getCreatedBy());
                sectionHeadFootnote.setCreatedAt(actSectionOld.getCreatedAt());
                sectionHeadFootnote.setUpdatedBy(getUsername());
                sectionHeadFootnote.setUpdatedAt(new Date());
            }
        }


        if (actSection.getSectionDescriptionFootnoteList() != null) {

            Integer tracker = 1;

            for (SectionDescriptionFootnoteForActSection sectionDescriptionFootnote : actSection.getSectionDescriptionFootnoteList()
                    ) {
                for (Integer i = sectionDescriptionFootnote.getFootnoteNumber() + 1; i <= actSection.getSectionDescriptionFootnoteList().size(); i++) {
                    List<SectionDescriptionFootnoteForActSection> FN = actSection.getSectionDescriptionFootnoteList();
                    Integer dupfnumber;
                    SectionDescriptionFootnoteForActSection oneFN = FN.get(actSection.getSectionDescriptionFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == actSection.getSectionDescriptionFootnoteList().size()) {

                        if (sectionDescriptionFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = sectionDescriptionFootnote.getFootnoteNumber();

                            List<SectionDescriptionFootnoteForActSection> footnotes = sectionDescriptionFootnoteActSectionRepository.getFootNoatFrom(dupfnumber, actSection.getId());
                            SectionDescriptionFootnoteForActSection footnotesOld = sectionDescriptionFootnoteActSectionRepository.getOneFootNoat(dupfnumber, actSection.getId());
                            actSection.getSectionDescriptionFootnoteList().get(actSection.getSectionDescriptionFootnoteList().size() - 1).setId(footnotesOld.getId());
                            Integer dlastfnid= sectionDescriptionFootnoteActSectionRepository.getLastId()+1;
                            for (SectionDescriptionFootnoteForActSection Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    actSection.getSectionDescriptionFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    actSection.getSectionDescriptionFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(dlastfnid);
                                    dlastfnid++;
                                }

                            }

                        }

                    }

                }
                if (tracker < actSection.getSectionDescriptionFootnoteList().size()) {
                    tracker = tracker + 1;
                }


                if (sectionDescriptionFootnote.getActSection() == null) {
                    sectionDescriptionFootnote.setActSection(actSection);
                }
                sectionDescriptionFootnote.setCreatedBy(actSectionOld.getCreatedBy());
                sectionDescriptionFootnote.setCreatedAt(actSectionOld.getCreatedAt());
                sectionDescriptionFootnote.setUpdatedBy(getUsername());
                sectionDescriptionFootnote.setUpdatedAt(new Date());

            }

        }


        if (actSection.getSectionTableFootnoteList() != null) {
            for (SectionTableFootnoteForActSection sectionTableFootnote : actSection.getSectionTableFootnoteList()
                    ) {
                if (sectionTableFootnote.getActSection() == null) {
                    sectionTableFootnote.setActSection(actSection);
                }

                sectionTableFootnote.setCreatedBy(actSectionOld.getCreatedBy());
                sectionTableFootnote.setCreatedAt(actSectionOld.getCreatedAt());
                sectionTableFootnote.setUpdatedBy(getUsername());
                sectionTableFootnote.setUpdatedAt(new Date());
            }
        }


        if (actSection.getVolume().getId() == 0) {
            actSection.setVolume(null);
        }
        if (actSection.getActId().getId() == 0) {
            actSection.setActId(null);
        }
        if (actSection.getPartId().getId() == 0) {
            actSection.setPartId(null);
        }
        if (actSection.getChapterId().getId() == 0) {
            actSection.setChapterId(null);
        }
        actSection.setCreatedBy(actSectionOld.getCreatedBy());
        actSection.setCreatedAt(actSectionOld.getCreatedAt());
        actSection.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actSection.setUpdatedAt(new Date());

       /* Set<SectionDescriptionFootnote> FNLIST =new HashSet<SectionDescriptionFootnote>();

        LinkedHashSet<SectionDescriptionFootnoteForActSection> hashSet = new LinkedHashSet<SectionDescriptionFootnoteForActSection>(actSection.getSectionDescriptionFootnoteList());

        ArrayList<SectionDescriptionFootnoteForActSection> listWithoutDuplicates = new ArrayList<>(hashSet);
        actSection.setSectionDescriptionFootnoteList(listWithoutDuplicates);
        actSection.getSectionDescriptionFootnoteList().forEach(da->System.out.println("DATA IS "+da));*/

        actSectionRepository.save(actSection);


        try {
            SimpleDateFormat sdf=new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
            SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

            if(sectionDescriptionFootnoteActSectionRepository.checkingDuplicate(id)!=null)
            {
            SectionDescriptionFootnoteForActSection sectionDescriptionFootnoteForActSectionAgain= sectionDescriptionFootnoteActSectionRepository.findFirst(id);
            String dateAgain =sectionDescriptionFootnoteForActSectionAgain.getUpdatedAt().toString();
            //System.out.println("AGAIN DATE IS"+dateAgain);
            String fromd = dateAgain;
            // System.out.println(fromd);
            Date fd = sdf.parse(fromd);
            //System.out.println(sdf2.format(fd));
                sectionDescriptionFootnoteActSectionRepository.deleteDuplicateFootnote(sdf2.format(fd),id);

            }
            if(sectionNameFootnoteActSectionRepository.checkingDuplicate(id)!=null)
            {
            SectionNameFootnoteForActSection sectionNameFootnoteForActSectionAgain= sectionNameFootnoteActSectionRepository.findFirst(id);
            String dateNameAgain =sectionNameFootnoteForActSectionAgain.getUpdatedAt().toString();
            //System.out.println("AGAIN DATE IS"+dateNameAgain);

            String fromdName = dateNameAgain;
            Date fdName = sdf.parse(fromdName);


                sectionNameFootnoteActSectionRepository.deleteDuplicateFootnote(sdf2.format(fdName),id);
            }

            if(sectionHeadFootnoteActSectionRepository.checkingDuplicate(id)!=null)
            {
            SectionHeadFootnoteForActSection sectionHeadFootnoteForActSectionAgain= sectionHeadFootnoteActSectionRepository.findFirst(id);
            String dateHeadAgain =sectionHeadFootnoteForActSectionAgain.getUpdatedAt().toString();

            String fromdHead = dateHeadAgain;
            Date fdHead = sdf.parse(fromdHead);
                sectionHeadFootnoteActSectionRepository.deleteDuplicateFootnote(sdf2.format(fdHead),id);
            }

        }
        catch (ParseException e) {
            System.out.println("IllegalArgumentException caught");
        }
        catch(NullPointerException e)
        {
            System.out.println("IllegalArgumentException caught");
        }
        return "redirect:/admin/actSection";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        ActSection actSection = actSectionRepository.findOne(id);
        model.addAttribute("actSection", actSection);
        return "admin/pages/ActSection/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        actSectionRepository.delete(id);
        Map<String, Object> response = new HashMap();
        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ActSection getById(@PathVariable("id") Integer id, Model model) {
        ActSection actSection = actSectionRepository.findOne(id);
        return actSection;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<ActSection> data() {
        return actSectionRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<ActSection> getActSections(@Valid @RequestBody DataTablesInput input) {

        return actSectionDataTableRepository.findAll(input);
    }


    @RequestMapping(value = "/act-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getActDropDownListByvolume(@RequestParam("volume.id") Integer volume) {
        return actRepository.getActDropDownListByvolume(volume);
    }


    @RequestMapping(value = "/actPart-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getActPartDropDownListByActId(@RequestParam("actId.id") Integer actId) {


        return actPartRepository.getActPartDropDownListByActId(actId);
    }


    @RequestMapping(value = "/actPart-lists", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getActChapterFromActDropDownListByPartId(@RequestParam(value = "actId.id") Integer actId) {


            return actChapterRepository.getActChapterDropDownListByActId(actId);

    }



    @RequestMapping(value = "/actChapter-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getActChapterDropDownListByPartId(@RequestParam(value = "actId.id") Integer actId, @RequestParam("partId.id") Integer partId) {
        //logger.info("PartId = "+partId.toString());
        //logger.info("ActId = "+actId.toString());
        if (partId == 0) {
            return actChapterRepository.getActChapterDropDownListByActIdAndPartIdIsNull(actId);
        } else {
            return actChapterRepository.getActChapterDropDownListByPartId(partId);
        }

    }

}
