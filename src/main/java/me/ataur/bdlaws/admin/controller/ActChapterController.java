package me.ataur.bdlaws.admin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import me.ataur.bdlaws.admin.model.ActChapter;
import me.ataur.bdlaws.admin.model.ChapterNameFootnoteForActChapter;
import me.ataur.bdlaws.admin.model.ChapterNoFootnoteForActChapter;
import me.ataur.bdlaws.admin.repository.*;
import me.ataur.bdlaws.admin.validator.ActChapterValidator;
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
 * @author Amran
 */
@Controller
@RequestMapping({"/admin/ActChapter", "/admin/actChapter"})
public class ActChapterController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(ActChapterController.class);
    @Autowired
    ActChapterRepository actChapterRepository;
    @Autowired
    ActChapterDataTableRepository actChapterDataTableRepository;
    @Autowired
    VolumeRepository volumeRepository;
    @Autowired
    ActRepository actRepository;
    @Autowired
    ActPartRepository actPartRepository;
    @Autowired
    ActChapterValidator actChapterValidator;
    @Autowired
    ChapterNameFootnoteForActChapterRepository chapterNameFootnoteForActChapterRepository;
    @Autowired
    ChapterNoFootnoteForActChapterRepository chapterNoFootnoteForActChapterRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/ActChapter/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        ActChapter actChapter = new ActChapter();
        model.addAttribute("actChapter", actChapter);
        model.addAttribute("action", "/create");

        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());

        model.addAttribute("actList", null);

        model.addAttribute("actPartList", null);

        return "admin/pages/ActChapter/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("actChapter") ActChapter actChapter, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {

        String attachment = uploadFile(attachmentFile);

        actChapter.setAttachment(null);

        if (attachment == null) {
            actChapter.setAttachment(null);
        } else {
            actChapter.setAttachment(attachment);
        }


        actChapterValidator.validate(actChapter,  result);
        if (result.hasErrors()) {
            model.addAttribute("actChapter", actChapter);
            model.addAttribute("action", "/create");

            model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
            model.addAttribute("actList", null);
            model.addAttribute("actPartList", null);
            return "admin/pages/ActChapter/create";
        }

        if (actChapter.getChapterNoFootnoteList() != null) {

            for (ChapterNoFootnoteForActChapter chapterNoFootnote : actChapter.getChapterNoFootnoteList()
                    ) {

                if (chapterNoFootnote.getActChapter() == null) {
                    chapterNoFootnote.setActChapter(actChapter);
                }
                chapterNoFootnote.setCreatedBy(getUsername());
                chapterNoFootnote.setCreatedAt(new Date());

            }
        }

        if (actChapter.getChapterNameFootnoteList() != null) {

            for (ChapterNameFootnoteForActChapter chapterNameFootnote : actChapter.getChapterNameFootnoteList()
                    ) {

                if (chapterNameFootnote.getActChapter() == null) {
                    chapterNameFootnote.setActChapter(actChapter);
                }
                chapterNameFootnote.setCreatedBy(getUsername());
                chapterNameFootnote.setCreatedAt(new Date());

            }
        }


        if (actChapter.getVolume().getId() == 0) {
            actChapter.setVolume(null);
        }
        if (actChapter.getActId().getId() == 0) {
            actChapter.setActId(null);
        }
        if (actChapter.getPartId().getId() == 0) {
            actChapter.setPartId(null);
        }
        actChapter.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actChapter.setCreatedAt(new Date());
        actChapterRepository.save(actChapter);
        model.addAttribute("actChapter",actChapterRepository.getNewActChapter());
        return "/admin/pages/ActChapter/details";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {


        ActChapter actChapter = actChapterRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("actChapter", actChapter);
        model.addAttribute("action", "/edit/" + id);
        model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
        model.addAttribute("actList", null);
        model.addAttribute("actPartList", null);
        return "admin/pages/ActChapter/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("actChapter") ActChapter actChapter, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {

        ActChapter actChapterOld = actChapterRepository.findOne(id);

        String attachment = uploadFile(attachmentFile);

        actChapter.setAttachment(null);

        if (attachment == null) {
            actChapter.setAttachment(actChapterOld.getAttachment());
        } else {
            actChapter.setAttachment(attachment);
        }

        actChapterValidator.validate(actChapter, result);
        if (result.hasErrors()) {
            model.addAttribute("actChapter", actChapter);
            model.addAttribute("action", "/edit/" + id);

            model.addAttribute("volumeList", volumeRepository.findAllByOrderByVolumeNameAsc());
            model.addAttribute("actList", null);
            model.addAttribute("actPartList", null);
            return "admin/pages/ActChapter/edit";
        }


        if (actChapter.getChapterNoFootnoteList() != null) {
            Integer tracker = 1;
            for (ChapterNoFootnoteForActChapter chapterNoFootnote : actChapter.getChapterNoFootnoteList()
                    ) {

                for (Integer i = chapterNoFootnote.getFootnoteNumber() + 1; i <= actChapter.getChapterNoFootnoteList().size(); i++) {
                    List<ChapterNoFootnoteForActChapter> FN = actChapter.getChapterNoFootnoteList();
                    Integer dupfnumber;
                    ChapterNoFootnoteForActChapter oneFN = FN.get(actChapter.getChapterNoFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == actChapter.getChapterNoFootnoteList().size()) {

                        if (chapterNoFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = chapterNoFootnote.getFootnoteNumber();

                            List<ChapterNoFootnoteForActChapter> footnotes = chapterNoFootnoteForActChapterRepository.getFootNoatFrom(dupfnumber, actChapter.getId());
                            ChapterNoFootnoteForActChapter footnotesOld = chapterNoFootnoteForActChapterRepository.getOneFootNoat(dupfnumber, actChapter.getId());
                            actChapter.getChapterNoFootnoteList().get(actChapter.getChapterNoFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer nolastfnid= chapterNoFootnoteForActChapterRepository.getLastId()+1;
                            for (ChapterNoFootnoteForActChapter Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    actChapter.getChapterNoFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    actChapter.getChapterNoFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(nolastfnid);
                                    nolastfnid++;
                                }

                            }


                        }

                    }

                }
                if (tracker < actChapter.getChapterNoFootnoteList().size()) {
                    tracker = tracker + 1;
                }

                if (chapterNoFootnote.getActChapter() == null) {
                    chapterNoFootnote.setActChapter(actChapter);
                }
                chapterNoFootnote.setCreatedBy(actChapterOld.getCreatedBy());
                chapterNoFootnote.setCreatedAt(actChapterOld.getCreatedAt());
                chapterNoFootnote.setUpdatedBy(getUsername());
                chapterNoFootnote.setUpdatedAt(new Date());


            }
        }

        if (actChapter.getChapterNameFootnoteList() != null) {
            Integer tracker = 1;
            for (ChapterNameFootnoteForActChapter chapterNameFootnote : actChapter.getChapterNameFootnoteList()
                    ) {
                for (Integer i = chapterNameFootnote.getFootnoteNumber() + 1; i <= actChapter.getChapterNameFootnoteList().size(); i++) {
                    List<ChapterNameFootnoteForActChapter> FN = actChapter.getChapterNameFootnoteList();
                    Integer dupfnumber;
                    ChapterNameFootnoteForActChapter oneFN = FN.get(actChapter.getChapterNameFootnoteList().size() - 1);
                    Integer laFN = oneFN.getFootnoteNumber();

                    if (tracker == actChapter.getChapterNameFootnoteList().size()) {

                        if (chapterNameFootnote.getFootnoteNumber() == laFN) {
                            dupfnumber = chapterNameFootnote.getFootnoteNumber();

                            List<ChapterNameFootnoteForActChapter> footnotes = chapterNameFootnoteForActChapterRepository.getFootNoatFrom(dupfnumber, actChapter.getId());
                            ChapterNameFootnoteForActChapter footnotesOld = chapterNameFootnoteForActChapterRepository.getOneFootNoat(dupfnumber, actChapter.getId());
                            actChapter.getChapterNameFootnoteList().get(actChapter.getChapterNameFootnoteList().size() - 1).setId(footnotesOld.getId());

                            Integer nalastfnid= chapterNameFootnoteForActChapterRepository.getLastId()+1;
                            for (ChapterNameFootnoteForActChapter Footnote : footnotes
                                    ) {
                                Integer fnTrack = 1;
                                System.out.println(footnotes.size());
                                if (fnTrack <= footnotes.size()) {
                                    actChapter.getChapterNameFootnoteList().get(Footnote.getFootnoteNumber() - 1).setFootnoteNumber(Footnote.getFootnoteNumber() + 1);
                                    actChapter.getChapterNameFootnoteList().get(Footnote.getFootnoteNumber() - 1).setId(nalastfnid);
                                    nalastfnid++;

                                }

                            }


                        }

                    }

                }
                if (tracker < actChapter.getChapterNameFootnoteList().size()) {
                    tracker = tracker + 1;
                }

                if (chapterNameFootnote.getActChapter() == null) {
                    chapterNameFootnote.setActChapter(actChapter);
                }
                chapterNameFootnote.setCreatedBy(actChapterOld.getCreatedBy());
                chapterNameFootnote.setCreatedAt(actChapterOld.getCreatedAt());
                chapterNameFootnote.setUpdatedBy(getUsername());
                chapterNameFootnote.setUpdatedAt(new Date());


            }
        }


        if (actChapter.getVolume().getId() == 0) {
            actChapter.setVolume(null);
        }
        if (actChapter.getActId().getId() == 0) {
            actChapter.setActId(null);
        }
        if (actChapter.getPartId().getId() == 0) {
            actChapter.setPartId(null);
        }
        actChapter.setCreatedBy(actChapterOld.getCreatedBy());
        actChapter.setCreatedAt(actChapterOld.getCreatedAt());
        actChapter.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actChapter.setUpdatedAt(new Date());
        actChapterRepository.save(actChapter);


        try {
            SimpleDateFormat sdf=new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
            SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

            if(chapterNoFootnoteForActChapterRepository.checkingDuplicate(id)!=null)
            {
                ChapterNoFootnoteForActChapter  chapterNoFootnoteForActChapterAgain= chapterNoFootnoteForActChapterRepository.findFirst(id);
                String dateAgain =chapterNoFootnoteForActChapterAgain.getUpdatedAt().toString();
                //System.out.println("AGAIN DATE IS"+dateAgain);
                String fromd = dateAgain;
                // System.out.println(fromd);
                Date fd = sdf.parse(fromd);
                //System.out.println(sdf2.format(fd));
                chapterNoFootnoteForActChapterRepository.deleteDuplicateFootnote(sdf2.format(fd),id);

            }
            if(chapterNameFootnoteForActChapterRepository.checkingDuplicate(id)!=null)
            {
                ChapterNameFootnoteForActChapter chapterNameFootnoteForActChapterAgain= chapterNameFootnoteForActChapterRepository.findFirst(id);
                String dateNameAgain =chapterNameFootnoteForActChapterAgain.getUpdatedAt().toString();
                //System.out.println("AGAIN DATE IS"+dateNameAgain);

                String fromdName = dateNameAgain;
                Date fdName = sdf.parse(fromdName);
                chapterNameFootnoteForActChapterRepository.deleteDuplicateFootnote(sdf2.format(fdName),id);
            }


        }
        catch (ParseException e) {
            System.out.println("IllegalArgumentException caught");
        }
        catch(NullPointerException e)
        {
            System.out.println("IllegalArgumentException caught");
        }

        return "redirect:/admin/actChapter";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        ActChapter actChapter = actChapterRepository.findOne(id);
        model.addAttribute("actChapter", actChapter);
        return "admin/pages/ActChapter/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        actChapterRepository.delete(id);
        Map<String, Object> response = new HashMap();

        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ActChapter getById(@PathVariable("id") Integer id, Model model) {
        ActChapter actChapter = actChapterRepository.findOne(id);
        return actChapter;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<ActChapter> data() {
        return actChapterRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<ActChapter> getActChapters(@Valid @RequestBody DataTablesInput input) {

        return actChapterDataTableRepository.findAll(input);
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


}
