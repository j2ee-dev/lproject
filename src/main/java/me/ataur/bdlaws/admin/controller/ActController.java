package me.ataur.bdlaws.admin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import me.ataur.bdlaws.admin.model.*;
import me.ataur.bdlaws.admin.repository.*;
import me.ataur.bdlaws.admin.validator.ActValidator;
import org.apache.commons.fileupload.FileUpload;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by imran
 */

@Controller
@RequestMapping({"/admin/Act", "/admin/act"})
public class ActController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(ActController.class);
    @Autowired
    ActRepository actRepository;
    @Autowired
    ActDataTableRepository actDataTableRepository;
    @Autowired
    VolumeRepository volumeRepository;
    @Autowired
    ActValidator actValidator;
    @Autowired
    PreambleFootnoteForActRepository preambleFootnoteForActRepository;
    @Autowired
    ActRoleFootnoteForActRepository actRoleFootnoteForActRepository;
    @Autowired
    me.ataur.bdlaws.admin.repository.shortTitleFootnoteForActRepository shortTitleFootnoteForActRepository;
    @Autowired
    ActScheduleForActRepository actScheduleForActRepository;
    @Autowired
    ActGazettedCopyForActRepository actGazettedCopyForActRepository;
    @Autowired
    ActRulesRegulationForActRepository actRulesRegulationForActRepository;
    @Autowired
    ActAttachmentRepository actAttachmentRepository;

    private static final String ABS_PATH = "/opt/servletFileUpload/";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/Act/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        Act act = new Act();
        model.addAttribute("act", act);
        model.addAttribute("action", "/create");
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        return "admin/pages/Act/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("act") Act act, BindingResult result, HttpServletRequest request, @RequestParam("preambleAttachmentFile") MultipartFile preambleAttachmentFile,@RequestParam("actScheduleAttachmentFile") MultipartFile[] actScheduleAttachmentFile,@RequestParam("actGazettedCopyListFile") MultipartFile[] actGazettedCopyListFile,@RequestParam("actRulesRegulatonAttachmentFile") MultipartFile[] actRulesRegulatonAttachmentFile,@RequestParam("actAttachmentFile") MultipartFile[] actAttachmentFile) {

        String preambleAttachment = uploadFile(preambleAttachmentFile);
        act.setPreambleAttachment(null);
        if (preambleAttachment == null) {
            act.setPreambleAttachment(null);
        } else {
            act.setPreambleAttachment(preambleAttachment);
        }

       /* String actAttachment = uploadFile(actAttachmentFile);
        act.setActAttachment(null);
        if (actAttachment == null) {
            act.setActAttachment(null);
        } else {
            act.setActAttachment(actAttachment);
        }*/


        model.addAttribute("act", act);
        model.addAttribute("action", "/create");
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());


        if (act.getShortTitleFootnoteList() != null) {
            for (ShortTitleFootnoteForAct shortTitleFootnote : act.getShortTitleFootnoteList()
                    ) {
                if (shortTitleFootnote.getAct() == null) {
                    shortTitleFootnote.setAct(act);
                }
                shortTitleFootnote.setCreatedBy(getUsername());
                shortTitleFootnote.setCreatedAt(new Date());
            }
        }

        if (act.getActRoleFootnoteList() != null) {
            for (ActRoleFootnoteForAct actRoleFootnote : act.getActRoleFootnoteList()
                    ) {
                if (actRoleFootnote.getAct() == null) {
                    actRoleFootnote.setAct(act);
                }
                actRoleFootnote.setCreatedBy(getUsername());
                actRoleFootnote.setCreatedAt(new Date());


            }
        }

        if (act.getPreambleFootnoteList() != null) {
            for (PreambleFootnoteForAct preambleFootnote : act.getPreambleFootnoteList()
                    ) {
                if (preambleFootnote.getAct() == null) {
                    preambleFootnote.setAct(act);
                }
                preambleFootnote.setCreatedBy(getUsername());
                preambleFootnote.setCreatedAt(new Date());
            }
        }


        List<String> actScheduleAttachmentList = new ArrayList<>();
        for (MultipartFile actScheduleAttachment : actScheduleAttachmentFile) {
            actScheduleAttachmentList.add(uploadFile(actScheduleAttachment));
        }


        if (act.getActScheduleList() != null) {

            Integer actScheduleIndex = new Integer(0);
            for (ActScheduleForAct actSchedule : act.getActScheduleList()
                    ) {
                if (actScheduleIndex != actScheduleAttachmentList.size()) {
                    if (actScheduleAttachmentList.get(actScheduleIndex) != null) {
                        actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));

                    }

                    if (actSchedule.getAct() == null) {
                        actSchedule.setAct(act);
                    }
                    actSchedule.setCreatedBy(getUsername());
                    actSchedule.setCreatedAt(new Date());

                    actScheduleIndex++;
                    /*if (actScheduleIndex == actScheduleAttachmentList.size()) {
                        break;
                    }*/
                }

            }
        }

    /*    if (act.getActScheduleList() != null) {
            List<String> actScheduleAttachmentList = new ArrayList<>();
            Integer actScheduleIndex = new Integer(0);
            for (ActScheduleForAct actScheduleForAct : act.getActScheduleList()
                    ) {
                List<MultipartFile> files = ((DefaultMultipartHttpServletRequest) request)
                        .getFiles("actScheduleAttachmentFile");
                actScheduleAttachmentList.add(uploadFile(files.get(actScheduleIndex)));

                actScheduleForAct.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                if (actScheduleForAct.getAct() == null) {
                    actScheduleForAct.setAct(act);
                    System.out.println("Create ActSchedule");
                }
                actScheduleIndex++;
            }
        }
*/
        List<String> actGazettedCopyAttachmentList = new ArrayList<>();
        for (MultipartFile actGazettedCopyAttachment : actGazettedCopyListFile) {
            actGazettedCopyAttachmentList.add(uploadFile(actGazettedCopyAttachment));
        }

        if (act.getActGazettedCopyList() != null) {

            Integer actGazettedCopyIndex = new Integer(0);
            for (ActGazettedCopyForAct actGazettedCopyForAct : act.getActGazettedCopyList()
                    ) {
                if (actGazettedCopyIndex != actGazettedCopyAttachmentList.size()) {
                    if (actGazettedCopyAttachmentList.get(actGazettedCopyIndex) != null) {
                        actGazettedCopyForAct.setAttachment(actGazettedCopyAttachmentList.get(actGazettedCopyIndex));
                    }

                    if (actGazettedCopyForAct.getAct() == null) {
                        actGazettedCopyForAct.setAct(act);
                    }
                    actGazettedCopyForAct.setCreatedBy(getUsername());
                    actGazettedCopyForAct.setCreatedAt(new Date());

                    actGazettedCopyIndex++;
                   /* if (actGazzatedCopyIndex == actScheduleAttachmentList.size()) {
                        break;
                    }*/
                }

            }
        }

        List<String> actRulesRegulationsAttachmentList = new ArrayList<>();
        for (MultipartFile actRulesRegulationAttachment : actRulesRegulatonAttachmentFile) {
            actRulesRegulationsAttachmentList.add(uploadFile(actRulesRegulationAttachment));
        }

        if (act.getActRulesRegulationList() != null) {

            Integer actRulesRegulationIndex = new Integer(0);
            for (ActRulesRegulation actGazettedCopyForAct : act.getActRulesRegulationList()
                    ) {
                if (actRulesRegulationIndex != actRulesRegulationsAttachmentList.size()) {
                    if (actRulesRegulationsAttachmentList.get(actRulesRegulationIndex) != null) {
                        actGazettedCopyForAct.setAttachment(actRulesRegulationsAttachmentList.get(actRulesRegulationIndex));
                    }

                    if (actGazettedCopyForAct.getAct() == null) {
                        actGazettedCopyForAct.setAct(act);
                    }
                    actGazettedCopyForAct.setCreatedBy(getUsername());
                    actGazettedCopyForAct.setCreatedAt(new Date());

                    actRulesRegulationIndex++;
                   /* if (actGazzatedCopyIndex == actScheduleAttachmentList.size()) {
                        break;
                    }*/
                }

            }
        }

        List<String> actAttachmentList = new ArrayList<>();
        for (MultipartFile  actAttachment : actAttachmentFile)
        {
            actAttachmentList.add(uploadFile(actAttachment));
        }

        if (act.getActAttachmentList() != null) {

            Integer actAttachmentIndex = new Integer(0);
            for (ActAttachment actAttachment : act.getActAttachmentList()
                    ) {
                if (actAttachmentIndex != actAttachmentList.size()) {
                    if (actAttachmentList.get(actAttachmentIndex) != null)
                    {
                        actAttachment.setAttachment(actAttachmentList.get(actAttachmentIndex));
                    }

                    if (actAttachment.getAct() == null) {
                        actAttachment.setAct(act);
                    }
                    actAttachment.setCreatedBy(getUsername());
                    actAttachment.setCreatedAt(new Date());

                    actAttachmentIndex++;
                   /* if (actGazzatedCopyIndex == actScheduleAttachmentList.size()) {
                        break;
                    }*/
                }

            }
        }


       /* if (act.getActGazettedCopyList() != null) {
            List<String> actGazettedCopyList = new ArrayList<>();
            Integer actGazettedIndex = new Integer(0);
            for (ActGazettedCopyForAct actGazettedCopyForAct : act.getActGazettedCopyList()
                    ) {
                List<MultipartFile> files = ((DefaultMultipartHttpServletRequest) request)
                        .getFiles("actScheduleAttachmentFile1");
                actScheduleAttachmentList1.add(uploadFile(files.get(actScheduleIndex1)));
                actGazettedCopyForAct.setAttachment(actScheduleAttachmentList1.get(actScheduleIndex1));
                if (actGazettedCopyForAct.getAct() == null) {
                    actGazettedCopyForAct.setAct(act);
                    System.out.println("Create ActGazettedCopyForAct");
                }
                actScheduleIndex1++;
            }
        }*/


      /*  if (act.getActScheduleList2() != null) {
            List<String> actScheduleAttachmentList2 = new ArrayList<>();
            Integer actScheduleIndex2 = new Integer(0);
            for (ActAttachment actAttachment : act.getActScheduleList2()
                    ) {
                List<MultipartFile> files = ((DefaultMultipartHttpServletRequest) request)
                        .getFiles("actScheduleAttachmentFile2");
                actScheduleAttachmentList2.add(uploadFile(files.get(actScheduleIndex2)));
                actAttachment.setAttachment(actScheduleAttachmentList2.get(actScheduleIndex2));
                if (actAttachment.getAct() == null) {
                    actAttachment.setAct(act);
                    System.out.println("Create Act Attachment");
                }
                actScheduleIndex2++;
            }
        }*/


        if (act.getVolume().getId() == 0) {
            act.setVolume(null);
        }
        act.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        act.setCreatedAt(new Date());
        actRepository.save(act);
        model.addAttribute("act", actRepository.getNewAct());
        return "admin/pages/Act/details";
        //return "redirect:/admin/act";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {


        Act act = actRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("act", act);
        model.addAttribute("action", "/edit/" + id);
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        return "admin/pages/Act/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("act") Act act, BindingResult result, HttpServletRequest request, @RequestParam("preambleAttachmentFile") MultipartFile preambleAttachmentFile,@RequestParam("actScheduleAttachmentFile") MultipartFile[] actScheduleAttachmentFile,@RequestParam("actGazettedCopyListFile") MultipartFile[] actGazettedCopyListFile,@RequestParam("actRulesRegulatonAttachmentFile") MultipartFile[] actRulesRegulatonAttachmentFile,@RequestParam("actAttachmentFile") MultipartFile[] actAttachmentFile) throws IllegalStateException, IOException {

        Act actOld = actRepository.findOne(id);
        String preambleAttachment = uploadFile(preambleAttachmentFile);
        act.setPreambleAttachment(null);
        if (preambleAttachment == null) {
            act.setPreambleAttachment(actOld.getPreambleAttachment());
        } else {
            act.setPreambleAttachment(preambleAttachment);
        }

        /*String actAttachment = uploadFile(actAttachmentFile);
        act.setActAttachment(null);
        if (actAttachment == null) {
            act.setActAttachment(actOld.getActAttachment());
        } else {
            act.setActAttachment(actAttachment);
        }*/

        //actValidator.validate(act, result);
        //if (result.hasErrors()) {
        model.addAttribute("act", act);
        model.addAttribute("action", "/edit/" + id);
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        //return "admin/pages/Act/edit";
        //}


        if (act.getShortTitleFootnoteList() != null) {
            Integer tracker = 1;
            for (ShortTitleFootnoteForAct shortTitleFootnote : act.getShortTitleFootnoteList()
                    ) {

                for (Integer i = shortTitleFootnote.getFootnoteNumber() + 1; i <= act.getShortTitleFootnoteList().size(); i++) {
                    List<ShortTitleFootnoteForAct> FN = act.getShortTitleFootnoteList();
                    Integer dupfnumber;
                    ShortTitleFootnoteForAct oneFN = FN.get(act.getShortTitleFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == act.getShortTitleFootnoteList().size()) {

                        if (shortTitleFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = shortTitleFootnote.getFootnoteNumber();

                            List<ShortTitleFootnoteForAct> footnotes = shortTitleFootnoteForActRepository.getFootNoatFrom(dupfnumber, act.getId());
                            ShortTitleFootnoteForAct footnotesOld = shortTitleFootnoteForActRepository.getOneFootNoat(dupfnumber, act.getId());
                            act.getShortTitleFootnoteList().get(act.getShortTitleFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer shlastfnid = shortTitleFootnoteForActRepository.getLastId()+1;
                            for (ShortTitleFootnoteForAct Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    act.getShortTitleFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    act.getShortTitleFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(shlastfnid);
                                    shlastfnid++;
                                }

                            }


                        }

                    }

                }
                if (tracker < act.getShortTitleFootnoteList().size()) {
                    tracker = tracker + 1;
                }

                if (shortTitleFootnote.getAct() == null) {
                    shortTitleFootnote.setAct(act);
                }
                shortTitleFootnote.setCreatedBy(actOld.getCreatedBy());
                shortTitleFootnote.setCreatedAt(actOld.getCreatedAt());
                shortTitleFootnote.setUpdatedBy(getUsername());
                shortTitleFootnote.setUpdatedAt(new Date());


            }
        }

        if (act.getActRoleFootnoteList() != null) {
            Integer tracker = 1;
            for (ActRoleFootnoteForAct actRoleFootnote : act.getActRoleFootnoteList()
                    ) {
                for (Integer i = actRoleFootnote.getFootnoteNumber() + 1; i <= act.getActRoleFootnoteList().size(); i++) {
                    List<ActRoleFootnoteForAct> FN = act.getActRoleFootnoteList();
                    Integer dupfnumber;
                    ActRoleFootnoteForAct oneFN = FN.get(act.getActRoleFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == act.getActRoleFootnoteList().size()) {

                        if (actRoleFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = actRoleFootnote.getFootnoteNumber();

                            List<ActRoleFootnoteForAct> footnotes = actRoleFootnoteForActRepository.getFootNoatFrom(dupfnumber, act.getId());
                            ActRoleFootnoteForAct footnotesOld = actRoleFootnoteForActRepository.getOneFootNoat(dupfnumber, act.getId());
                            act.getActRoleFootnoteList().get(act.getActRoleFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer rlastfnid = actRoleFootnoteForActRepository.getLastId()+1;
                            for (ActRoleFootnoteForAct Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    act.getActRoleFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    act.getActRoleFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(rlastfnid);
                                    rlastfnid++;
                                }

                            }


                        }

                    }

                }
                if (tracker < act.getActRoleFootnoteList().size()) {
                    tracker = tracker + 1;
                }

                if (actRoleFootnote.getAct() == null) {
                    actRoleFootnote.setAct(act);
                }
                actRoleFootnote.setCreatedBy(actOld.getCreatedBy());
                actRoleFootnote.setCreatedAt(actOld.getCreatedAt());
                actRoleFootnote.setUpdatedBy(getUsername());
                actRoleFootnote.setUpdatedAt(new Date());
            }
        }

        if (act.getPreambleFootnoteList() != null) {
            Integer tracker = 1;
            for (PreambleFootnoteForAct preambleFootnote : act.getPreambleFootnoteList()
                    ) {
                for (Integer i = preambleFootnote.getFootnoteNumber() + 1; i <= act.getPreambleFootnoteList().size(); i++) {
                    List<PreambleFootnoteForAct> FN = act.getPreambleFootnoteList();
                    Integer dupfnumber;
                    PreambleFootnoteForAct oneFN = FN.get(act.getPreambleFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == act.getPreambleFootnoteList().size()) {

                        if (preambleFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = preambleFootnote.getFootnoteNumber();

                            List<PreambleFootnoteForAct> footnotes = preambleFootnoteForActRepository.getFootNoatFrom(dupfnumber, act.getId());
                            PreambleFootnoteForAct footnotesOld = preambleFootnoteForActRepository.getOneFootNoat(dupfnumber, act.getId());
                            act.getPreambleFootnoteList().get(act.getPreambleFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer plastfnid = preambleFootnoteForActRepository.getLastId()+1;
                            for (PreambleFootnoteForAct Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    act.getPreambleFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    act.getPreambleFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(plastfnid);
                                    plastfnid++;
                                }

                            }


                        }

                    }

                }
                if (tracker < act.getPreambleFootnoteList().size()) {
                    tracker = tracker + 1;
                }

                if (preambleFootnote.getAct() == null) {
                    preambleFootnote.setAct(act);
                }
                preambleFootnote.setCreatedBy(actOld.getCreatedBy());
                preambleFootnote.setCreatedAt(actOld.getCreatedAt());
                preambleFootnote.setUpdatedBy(getUsername());
                preambleFootnote.setUpdatedAt(new Date());


            }
        }


        /*if ((attachFileObj != null) && (attachFileObj.length > 0) && (!attachFileObj.equals(""))) {
            for (CommonsMultipartFile file : attachFileObj) {
                if (file.isEmpty()) {
                    continue;
                }
                if (!file.getOriginalFilename().isEmpty()) {
                    BufferedOutputStream outputStream = new BufferedOutputStream(
                            new FileOutputStream(new File(ABS_PATH, file.getOriginalFilename())));
                    act.setActAttachment(file.getOriginalFilename());
                    outputStream.write(file.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
            }
        }*/

        /*if (act.getActScheduleList() != null){
            for (ActScheduleForAct actScheduleForAct : act.getActScheduleList()
                    ) {
            if ((attachFileObj != null) && (attachFileObj.length > 0) && (!attachFileObj.equals(""))) {

                    for (CommonsMultipartFile file : attachFileObj) {
                        if (!file.getOriginalFilename().isEmpty()) {
                            BufferedOutputStream outputStream = new BufferedOutputStream(
                                    new FileOutputStream(new File(ABS_PATH, file.getOriginalFilename())));
                            actScheduleForAct.setAttachment(file.getOriginalFilename());
                            outputStream.write(file.getBytes());
                            outputStream.flush();
                            outputStream.close();
                        }
                    }
                    if (actScheduleForAct.getAct() == null) {
                        actScheduleForAct.setAct(act);
                    }
                    actScheduleForAct.setCreatedBy(getUsername());
                    actScheduleForAct.setCreatedAt(new Date());
                }
            }
        }*/


      /*  if (act.getActScheduleList() != null) {
            List<String> actScheduleAttachmentList = new ArrayList<>();
           *//* for (MultipartFile actScheduleAttachment : actScheduleAttachmentFile) {
                actScheduleAttachmentList.add(uploadFile(actScheduleAttachment));
            }*//*

            Integer actScheduleIndex = new Integer(0);
            for (ActScheduleForAct actScheduleForAct : act.getActScheduleList()
                    ) {
                //Integer actScheduleIndex = new Integer(0);
                List<MultipartFile> files = ((DefaultMultipartHttpServletRequest) request)
                        .getFiles("actScheduleAttachmentFile");
                actScheduleAttachmentList.add(uploadFile(files.get(actScheduleIndex)));

                actScheduleForAct.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                if (actScheduleForAct.getAct() == null) {
                    actScheduleForAct.setAct(act);
                }
                //actScheduleForAct.setCreatedBy(getUsername());
                //actScheduleForAct.setCreatedAt(new Date());
                actScheduleIndex++;
            }
        }*/

        List<String> actScheduleAttachmentList = new ArrayList<>();
        List<MultipartFile> multipartFileList =new ArrayList<>();
        for (MultipartFile actScheduleAttachment : actScheduleAttachmentFile) {
            actScheduleAttachmentList.add(uploadFile(actScheduleAttachment));
            multipartFileList.add(actScheduleAttachment);
        }


        if (act.getActScheduleList() != null) {

            Integer actScheduleIndex = new Integer(0);
            for (ActScheduleForAct actSchedule : act.getActScheduleList()
                    ) {
                List<ActScheduleForAct> previousActs = actScheduleForActRepository.findAllByActId(actOld.getId());

                if (previousActs.size() != 0) {
                    for (ActScheduleForAct actScheduleForAct : previousActs)
                    {
                        if ( actSchedule.getId() != null && actSchedule.getId().equals(actScheduleForAct.getId()) && !multipartFileList.get(actScheduleIndex).getContentType().equals("application/octet-stream"))
                        {
                            actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                        }
                        else if(actSchedule.getId() != null && actSchedule.getId().equals(actScheduleForAct.getId()) && multipartFileList.get(actScheduleIndex).getContentType().equals("application/octet-stream"))
                        {
                            actSchedule.setAttachment(actScheduleForAct.getAttachment());
                        }
                        else if (actSchedule.getId() == null)
                        {
                            actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                        }
                    }
                    if (actScheduleAttachmentList.get(actScheduleIndex) != null) {
                        actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                    }

                } else {
                    if (actScheduleAttachmentList.get(actScheduleIndex) != null) {
                        actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                    }
                }

            /*    if (actScheduleAttachmentList.get(actScheduleIndex) != null) {
                    actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                }*/
                if (actSchedule.getAct() == null) {
                    actSchedule.setAct(act);
                }
                actSchedule.setCreatedBy(actOld.getCreatedBy());
                actSchedule.setCreatedAt(actOld.getCreatedAt());
                actSchedule.setUpdatedBy(getUsername());
                actSchedule.setUpdatedAt(new Date());
                actScheduleIndex++;
            }
        }

        List<String> actGazettedCopyList = new ArrayList<>();
        List<MultipartFile> multipartFileList1 = new ArrayList<>();
        for (MultipartFile actGazettedCopyAttachment : actGazettedCopyListFile) {
            actGazettedCopyList.add(uploadFile(actGazettedCopyAttachment));
            multipartFileList1.add(actGazettedCopyAttachment);
        }


        if (act.getActGazettedCopyList()!= null) {

            Integer actGazettedCopyIndex = new Integer(0);
            for (ActGazettedCopyForAct actGazettedCopy : act.getActGazettedCopyList()
                    ) {
                List<ActGazettedCopyForAct> previousActs = actGazettedCopyForActRepository.findAllByActId(actOld.getId());

                if (previousActs.size() != 0) {
                    for (ActGazettedCopyForAct actGazettedCopyForAct : previousActs)
                    {
                        if( actGazettedCopy.getId() != null && actGazettedCopy.getId().equals(actGazettedCopyForAct.getId()) && !multipartFileList1.get(actGazettedCopyIndex).getContentType().equals("application/octet-stream"))
                        {
                            actGazettedCopy.setAttachment(actGazettedCopyList.get(actGazettedCopyIndex));
                        }
                        else if(actGazettedCopy.getId() != null && actGazettedCopy.getId().equals(actGazettedCopyForAct.getId()) && multipartFileList1.get(actGazettedCopyIndex).getContentType().equals("application/octet-stream"))
                        {
                            actGazettedCopy.setAttachment(actGazettedCopyForAct.getAttachment());
                        }
                        else if(actGazettedCopy.getId() == null)
                        {
                            actGazettedCopy.setAttachment(actGazettedCopyList.get(actGazettedCopyIndex));
                        }
                    }
                    if (actGazettedCopyList.get(actGazettedCopyIndex) != null)
                    {
                        actGazettedCopy.setAttachment(actGazettedCopyList.get(actGazettedCopyIndex));
                    }

                } else {
                    if (actGazettedCopyList.get(actGazettedCopyIndex) != null) {
                        actGazettedCopy.setAttachment(actGazettedCopyList.get(actGazettedCopyIndex));
                    }
                }

            /*    if (actScheduleAttachmentList.get(actScheduleIndex) != null) {
                    actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                }*/
                if (actGazettedCopy.getAct() == null) {
                    actGazettedCopy.setAct(act);
                }
                actGazettedCopy.setCreatedBy(actOld.getCreatedBy());
                actGazettedCopy.setCreatedAt(actOld.getCreatedAt());
                actGazettedCopy.setUpdatedBy(getUsername());
                actGazettedCopy.setUpdatedAt(new Date());
                actGazettedCopyIndex++;
            }
        }

        List<String> actRulesRegulationList = new ArrayList<>();
        List<MultipartFile> multipartFileList3 = new ArrayList<>();
        for (MultipartFile actRulesRegulationAttachment : actRulesRegulatonAttachmentFile) {
            actRulesRegulationList.add(uploadFile(actRulesRegulationAttachment));
            multipartFileList3.add(actRulesRegulationAttachment);
        }


        if (act.getActRulesRegulationList()!= null) {

            Integer actRulesRegulationIndex = new Integer(0);
            for (ActRulesRegulation actGazettedCopy : act.getActRulesRegulationList()
                    ) {
                List<ActRulesRegulation> previousActs = actRulesRegulationForActRepository.findAllByActId(actOld.getId());

                if (previousActs.size() != 0) {
                    for (ActRulesRegulation actGazettedCopyForAct : previousActs)
                    {
                        if( actGazettedCopy.getId() != null && actGazettedCopy.getId().equals(actGazettedCopyForAct.getId()) && !multipartFileList3.get(actRulesRegulationIndex).getContentType().equals("application/octet-stream"))
                        {
                            actGazettedCopy.setAttachment(actRulesRegulationList.get(actRulesRegulationIndex));
                        }
                        else if(actGazettedCopy.getId() != null && actGazettedCopy.getId().equals(actGazettedCopyForAct.getId()) && multipartFileList3.get(actRulesRegulationIndex).getContentType().equals("application/octet-stream"))
                        {
                            actGazettedCopy.setAttachment(actGazettedCopyForAct.getAttachment());
                        }
                        else if(actGazettedCopy.getId() == null)
                        {
                            actGazettedCopy.setAttachment(actRulesRegulationList.get(actRulesRegulationIndex));
                        }
                    }
                    if (actRulesRegulationList.get(actRulesRegulationIndex) != null)
                    {
                        actGazettedCopy.setAttachment(actRulesRegulationList.get(actRulesRegulationIndex));
                    }

                } else {
                    if (actRulesRegulationList.get(actRulesRegulationIndex) != null) {
                        actGazettedCopy.setAttachment(actRulesRegulationList.get(actRulesRegulationIndex));
                    }
                }

            /*    if (actScheduleAttachmentList.get(actScheduleIndex) != null) {
                    actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                }*/
                if (actGazettedCopy.getAct() == null) {
                    actGazettedCopy.setAct(act);
                }
                actGazettedCopy.setCreatedBy(actOld.getCreatedBy());
                actGazettedCopy.setCreatedAt(actOld.getCreatedAt());
                actGazettedCopy.setUpdatedBy(getUsername());
                actGazettedCopy.setUpdatedAt(new Date());
                actRulesRegulationIndex++;
            }
        }

        List<String> actAttachmentList = new ArrayList<>();
        List<MultipartFile> multipartFileList2 = new ArrayList<>();
        for (MultipartFile actAttachment : actAttachmentFile)
        {
            actAttachmentList.add(uploadFile(actAttachment));
            multipartFileList2.add(actAttachment);
        }

        if (act.getActAttachmentList()!= null)
        {

            Integer actAttachmentIndex = new Integer(0);
            for (ActAttachment actAttachment : act.getActAttachmentList()
                    ) {
                List<ActAttachment> previousActs = actAttachmentRepository.findAllByActId(actOld.getId());

                if (previousActs.size() != 0) {
                    for (ActAttachment actAttachmentForAct : previousActs)
                    {
                        if(actAttachment.getId() != null)
                        {
                            if ( actAttachment.getId() != null && actAttachment.getId().equals(actAttachmentForAct.getId()) && !multipartFileList2.get(actAttachmentIndex).getContentType().equals("application/octet-stream"))
                            {
                                actAttachment.setAttachment(actAttachmentList.get(actAttachmentIndex));
                            }
                            else if(actAttachment.getId() != null && actAttachment.getId().equals(actAttachmentForAct.getId()) && multipartFileList2.get(actAttachmentIndex).getContentType().equals("application/octet-stream"))
                            {
                                actAttachment.setAttachment(actAttachmentForAct.getAttachment());
                            }
                            else if (actAttachment.getId() == null)
                            {
                                actAttachment.setAttachment(actAttachmentList.get(actAttachmentIndex));
                            }
                        }

                    }
                    if (actAttachmentList.get(actAttachmentIndex) != null)
                    {
                        actAttachment.setAttachment(actAttachmentList.get(actAttachmentIndex));
                    }

                } else {
                    if (actAttachmentList.get(actAttachmentIndex) != null) {
                        actAttachment.setAttachment(actAttachmentList.get(actAttachmentIndex));
                    }

                }

            /*    if (actScheduleAttachmentList.get(actScheduleIndex) != null) {
                actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
            }*/
            if (actAttachment.getAct() == null) {
                actAttachment.setAct(act);
            }
            actAttachment.setCreatedBy(actOld.getCreatedBy());
            actAttachment.setCreatedAt(actOld.getCreatedAt());
            actAttachment.setUpdatedBy(getUsername());
            actAttachment.setUpdatedAt(new Date());
            actAttachmentIndex++;
        }
    }

      /* List<String> actAttachmentList = new ArrayList<>();
        List<MultipartFile> multipartFileList2 = new ArrayList<>();
        for (MultipartFile actAttachment : actAttachmentFile)
        {
            actAttachmentList.add(uploadFile(actAttachment));
            multipartFileList2.add(actAttachment);
        }

        if (act.getActAttachmentList()!= null)
        {

            Integer actAttachmentIndex = new Integer(0);
            for (ActAttachment actAttachment : act.getActAttachmentList()
                    ) {
                List<ActAttachment> previousActs = actAttachmentRepository.findAllByActId(actOld.getId());

                if (previousActs.size() != 0) {
                    for (ActAttachment actAttachmentForAct : previousActs)
                    {
                       if(actAttachment.getId() != null)
                       {
                           if ( actAttachment.getId() != null && actAttachment.getId().equals(actAttachmentForAct.getId()) && !multipartFileList2.get(actAttachmentIndex).getContentType().equals("application/octet-stream"))
                           {
                               actAttachment.setAttachment(actAttachmentList.get(actAttachmentIndex));
                           }
                           else if(actAttachment.getId() != null && actAttachment.getId().equals(actAttachmentForAct.getId()) && multipartFileList2.get(actAttachmentIndex).getContentType().equals("application/octet-stream"))
                           {
                               actAttachment.setAttachment(actAttachmentForAct.getAttachment());
                           }
                           else if (actAttachment.getId() == null)
                           {
                               actAttachment.setAttachment(actAttachmentList.get(0));
                           }
                       }

                    }
                    *//*if (actAttachmentList.get(actAttachmentIndex) != null)
                    {
                        actAttachment.setAttachment(actAttachmentList.get(actAttachmentIndex));
                    }*//*
                    if (actAttachment.getId() == null)
                    {
                        actAttachment.setAttachment(actAttachmentList.get(0));
                    }

                } else {
                    if (actAttachmentList.get(actAttachmentIndex) != null) {
                        actAttachment.setAttachment(actAttachmentList.get(actAttachmentIndex));
                    }

                }

            *//*    if (actScheduleAttachmentList.get(actScheduleIndex) != null) {
                    actSchedule.setAttachment(actScheduleAttachmentList.get(actScheduleIndex));
                }*//*
                if (actAttachment.getAct() == null) {
                    actAttachment.setAct(act);
                }
                actAttachment.setCreatedBy(actOld.getCreatedBy());
                actAttachment.setCreatedAt(actOld.getCreatedAt());
                actAttachment.setUpdatedBy(getUsername());
                actAttachment.setUpdatedAt(new Date());
                actAttachmentIndex++;
            }
        }
*/

      /*  if (act.getActScheduleList1() != null) {
            List<String> actScheduleAttachmentList1 = new ArrayList<>();
            for (ActGazettedCopyForAct actGazettedCopyForAct : act.getActScheduleList1()
                    ) {
                Integer actScheduleIndex1 = new Integer(0);

                List<MultipartFile> files = ((DefaultMultipartHttpServletRequest) request)
                        .getFiles("actScheduleAttachmentFile1");
                actScheduleAttachmentList1.add(uploadFile(files.get(actScheduleIndex1)));

                actGazettedCopyForAct.setAttachment(actScheduleAttachmentList1.get(actScheduleIndex1));
                if (actGazettedCopyForAct.getAct() == null) {
                    actGazettedCopyForAct.setAct(act);
                    System.out.println("Edit ActGazettedCopyForAct");
                }
                //actGazettedCopyForAct.setCreatedBy(actOld.getCreatedBy());
                //actGazettedCopyForAct.setCreatedAt(actOld.getUpdatedAt());
                //actGazettedCopyForAct.setUpdatedBy(getUsername());
                //actGazettedCopyForAct.setUpdatedAt(new Date());
            }
        }
*/

        /*if (act.getActAttachmentList()!= null) {
            List<String> actAttachmentList = new ArrayList<>();
            for (ActAttachment actAttachment : act.getActAttachmentList()
                    ) {
                Integer actScheduleIndex2 = new Integer(0);

                List<MultipartFile> files = ((DefaultMultipartHttpServletRequest) request)
                        .getFiles("actScheduleAttachmentFile2");
                actScheduleAttachmentList2.add(uploadFile(files.get(actScheduleIndex2)));

                actAttachment.setAttachment(actScheduleAttachmentList2.get(actScheduleIndex2));
                if (actAttachment.getAct() == null) {
                    actAttachment.setAct(act);
                    System.out.println("Edit Act Gazzeted");
                }
            }
        }*/

        if (act.getVolume().getId() == 0) {
            act.setVolume(null);
        }

        act.setCreatedBy(actOld.getCreatedBy());
        act.setCreatedAt(actOld.getCreatedAt());
        act.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        act.setUpdatedAt(new Date());
        actRepository.save(act);



        try {
            SimpleDateFormat sdf=new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
            SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

            if(shortTitleFootnoteForActRepository.checkingDuplicate(id)!=null)
            {
                ShortTitleFootnoteForAct shortTitleFootnoteForActAgain= shortTitleFootnoteForActRepository.findFirst(id);
                String dateAgain =shortTitleFootnoteForActAgain.getUpdatedAt().toString();
                //System.out.println("AGAIN DATE IS"+dateAgain);
                String fromd = dateAgain;
                // System.out.println(fromd);
                Date fd = sdf.parse(fromd);
                //System.out.println(sdf2.format(fd));
                shortTitleFootnoteForActRepository.deleteDuplicateFootnote(sdf2.format(fd),id);

            }
            if(actRoleFootnoteForActRepository.checkingDuplicate(id)!=null)
            {
                ActRoleFootnoteForAct actRoleFootnoteForActAgain= actRoleFootnoteForActRepository.findFirst(id);
                String dateNameAgain =actRoleFootnoteForActAgain.getUpdatedAt().toString();
                //System.out.println("AGAIN DATE IS"+dateNameAgain);

                String fromdName = dateNameAgain;
                Date fdName = sdf.parse(fromdName);

                actRoleFootnoteForActRepository.deleteDuplicateFootnote(sdf2.format(fdName),id);
            }

            if(preambleFootnoteForActRepository.checkingDuplicate(id)!=null)
            {
                PreambleFootnoteForAct preambleFootnoteForActAgain= preambleFootnoteForActRepository.findFirst(id);
                String dateHeadAgain =preambleFootnoteForActAgain.getUpdatedAt().toString();

                String fromdHead = dateHeadAgain;
                Date fdHead = sdf.parse(fromdHead);
                preambleFootnoteForActRepository.deleteDuplicateFootnote(sdf2.format(fdHead),id);
            }

        }
        catch (ParseException e) {
            System.out.println("IllegalArgumentException caught");
        }
        catch(NullPointerException e)
        {
            System.out.println("IllegalArgumentException caught");
        }



        return "redirect:/admin/act";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        Act act = actRepository.findOne(id);
        model.addAttribute("act", act);
        return "admin/pages/Act/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        actRepository.delete(id);
        Map<String, Object> response = new HashMap();

        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Act getById(@PathVariable("id") Integer id, Model model) {
        Act act = actRepository.findOne(id);
        return act;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<Act> data() {
        return actRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<Act> getActs(@Valid @RequestBody DataTablesInput input) {

        return actDataTableRepository.findAll(input);
    }


    @RequestMapping(value = "/newActDetails")
    public String newDetails(Model model) {
        model.addAttribute("newActDetails", actRepository.getNewAct());
        return "admin/pages/Act/newActDetails";
    }

}
