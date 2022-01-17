

package me.ataur.bdlaws.website.controller;


import eu.bitwalker.useragentutils.UserAgent;
import me.ataur.bdlaws.admin.model.*;
import me.ataur.bdlaws.admin.repository.*;

import me.ataur.bdlaws.admin.validator.FeedBackValidator;
import me.ataur.bdlaws.login.util.VerifyRecaptcha;
import me.ataur.bdlaws.website.model.AdvancedSearch;
import me.ataur.bdlaws.website.model.AdvancedSearchForm;
import me.ataur.bdlaws.website.model.Search;
import me.ataur.bdlaws.website.repository.*;
import org.apache.bcel.generic.NEW;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;


/**
 * @author Amran
 */
@Controller
public class HomeController extends MyWebBaseController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    VolumeRepository volumeRepository;
    @Autowired
    ActRepository actRepository;
    @Autowired
    ActPartRepository actPartRepository;
    @Autowired
    ActChapterRepository actChapterRepository;
    @Autowired
    ActSectionRepository actSectionRepository;
    @Autowired
    RelatedLinkRepository relatedLinkRepository;
    @Autowired
    FaqRepository faqRepository;
    @Autowired
    StaticPageRepository staticPageRepository;
    @Autowired
    FeedBackRepository feedBackRepository;

    @Autowired
    FeedBackValidator feedBackValidator;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private ActSearchRepository actSearchRepository;
    @Autowired
    private PartSearchRepository partSearchRepository;
    @Autowired
    private ChapterSearchRepository chapterSearchRepository;
    @Autowired
    private SectionSearchRepository sectionSearchRepository;
    @Autowired
    private AdvancedSearchRepository advancedSearchRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    VerifyRecaptcha verifyRecaptcha;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);


    }


    @RequestMapping(value = {"", "/", "index.html"}, method = RequestMethod.GET)
    public String indexPage(Model model, Locale locale,@RequestParam(name = "language", required = false) String language, HttpServletRequest request,final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "home");
        model.addAttribute("language",language);
        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");

        //List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
       // List<BdCodeVolume> volumeList = bdCodeVolumeRepository.findByStatusTrueOrderByIdAsc();
       // model.addAttribute("volumes", volumeList);
        return "website/index";
    }


/*
    @RequestMapping(value = {"bdcode-volume-pdf-{id}.html"}, method = RequestMethod.GET)
    public String indexPage(Model model, Locale locale, @PathVariable("id") Integer id, HttpServletRequest request) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());

        model.addAttribute("page", "bangladesh-code-volume");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        BdCodeVolume volume = bdCodeVolumeRepository.findByVolumeNumber(id);
        model.addAttribute("volume", volume);
        List<BdCodeAct> actList = bdCodeActRepository.findByVolumeOrderByIdAsc(volume);
        model.addAttribute("acts", actList);

        List<BdCodeVolume> volumeList = bdCodeVolumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        return "website/index";
    }
*/


    @RequestMapping(value = {"laws-of-bangladesh.html"}, method = RequestMethod.GET)
    public String lawsOfBangladesh(Model model, Locale locale,
                                   @RequestParam(name = "q", required = false) String q,
                                   @RequestParam(name = "type", required = false) Integer type,
                                   @Valid @ModelAttribute("search") AdvancedSearchForm search,
                                    HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "laws-of-bangladesh");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);


        model.addAttribute("type", type);
        model.addAttribute("q", q);
        //model.addAttribute("page", "search");
        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }
/*
    @RequestMapping(value = {"codes-of-bangladesh.html"}, method = RequestMethod.GET)
    public String codesOfBangladesh(Model model, Locale locale,
                                   HttpServletRequest request) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "codes-of-bangladesh");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

      */
/*  List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);*//*


        List<BdCodeVolume> volumeList = bdCodeVolumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        return "website/index";
    }
*/

    @RequestMapping(value = {"laws-of-bangladesh-chronological-index.html"}, method = RequestMethod.GET)
    public String lawsOfBangladeshChronologicalIndex(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "laws-of-bangladesh-chronological-index");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        //List<Act> acts = actRepository.findByStatusIsTrueOrderByIdAscOrderNoAsc();
        List<Act> acts = actRepository.getAllActsByActiveVolume();
        model.addAttribute("acts", acts);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"laws-of-bangladesh-alphabetical-index.html"}, method = RequestMethod.GET)
    public String lawsOfBangladeshAlphabeticalIndex(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "laws-of-bangladesh-alphabetical-index");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        List<Act> acts = actRepository.findByStatusIsTrueOrderByShortTitleAsc();
        model.addAttribute("acts", acts);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"about-bangladesh-code.html"}, method = RequestMethod.GET)
    public String aboutBangladeshCode(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        StaticPage staticPage = staticPageRepository.findByPageUrl("about-bangladesh-code.html");

        model.addAttribute("page", "static-page");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        model.addAttribute("staticPage", staticPage);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


/*
    @RequestMapping(value = {"bangladesh-code-chronological-index.html"}, method = RequestMethod.GET)
    public String bangladeshCodeByChronologicalIndex(Model model, Locale locale, HttpServletRequest request) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "bangladesh-code-chronological-index");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));
        List<BdCodeAct> acts = bdCodeActRepository.getAllActsByActiveVolume();
        model.addAttribute("acts", acts);
        return "website/index";
    }
*/


/*
    @RequestMapping(value = {"bangladesh-code-alphabetical-index.html"}, method = RequestMethod.GET)
    public String bangladeshCodeByAlphabeticalIndex(*/
/*Model model,
                                                    @RequestParam(name = "q", required = false) String q,
                                                    @RequestParam(name = "type", required = false) Integer type,
                                                    @Valid @ModelAttribute("search") AdvancedSearchForm search,
                                                    Locale locale, HttpServletRequest request*//*
Model model, Locale locale, HttpServletRequest request) {
        */
/*hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "laws-of-bangladesh");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription","WHEREAS it is expedient to consolidate the law applicable to intestate and testamentary succession in Bangladesh; it is hereby enacted as follows:-ion", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        model.addAttribute("type", type);
        model.addAttribute("q", q);
        //model.addAttribute("page", "search");
        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        return "website/index";*//*


        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "bangladesh-code-alphabetical-index");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        List<BdCodeAct> acts = bdCodeActRepository.findByStatusIsTrueOrderByShortTitleAsc();
        model.addAttribute("acts", acts);
        return "website/index";
    }
*/


    @RequestMapping(value = {"volume-{volumneNumber}.html"}, method = RequestMethod.GET)
    public String volumeLaws(Model model,
                             Locale locale,
                             @PathVariable("volumneNumber") Integer volumneNumber, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());

        Volume volume = volumeRepository.findByVolumeNumberAndStatusIsTrue(volumneNumber);


        if (volume == null) {
            return "redirect:/404.html";
        }
        model.addAttribute("page", "volume");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));


        model.addAttribute("volume", volume);

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        //List<Act> acts = actRepository.findByVolumeOrderByIdAsc(volume);
        List<Act> acts = actRepository.findByVolumeOrderByOrderNoAsc(volume);
        model.addAttribute("acts", acts);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"act-by-alphabet.html"}, method = RequestMethod.GET)
    public String actByAlphabet(Model model,
                                @RequestParam(name = "alp", required = false) String alp,
                             Locale locale,
                             HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        if (alp == null) {
           return "redirect:/404.html";
        }
       model.addAttribute("page", "actbyalphabet");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));


        model.addAttribute("alphabet", alp);

         List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        List<Act> acts = actRepository.findAllByStartingAlphabet(alp);
        model.addAttribute("acts", acts);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"act-{id}.html"}, method = RequestMethod.GET)
    public String volumeLawDetails(Model model,
                                   Locale locale,
                                   @RequestParam(name = "hl", required = false) String hl,
                                   @PathVariable("id") Integer id, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(id);

        if (act == null) {
            return "redirect:/404.html";
        }

        model.addAttribute("page", "act");
        model.addAttribute("metaTitle", act.getShortTitle());
        model.addAttribute("metaDescription", act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", "));

        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));
        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        model.addAttribute("act", act);

        List<ActSection> sections = actSectionRepository.findByActIdAndStatusIsTrueOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(act);
        model.addAttribute("sections", sections);
        model.addAttribute("hl",hl);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }
    /*@RequestMapping(value = {"bdcode-act-pdf-{id}.html"}, method = RequestMethod.GET)
    public String bdCodeVolumeDetails(Model model,
                                   Locale locale,
                                   @RequestParam(name = "hl", required = false) String hl,
                                   @PathVariable("id") Integer id, HttpServletRequest request) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        BdCodeAct act = bdCodeActRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(id);

        if (act == null) {
            return "redirect:/404.html";
        }

        model.addAttribute("page", "bangladesh-code-act");
        model.addAttribute("metaTitle", act.getShortTitle());
        model.addAttribute("metaDescription", "WHEREAS it is expedient to consolidate the law applicable to intestate and testamentary succession in Bangladesh; it is hereby enacted as follows:-ion"*//*act.getActRole() + act.getPreamble()*//*);
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", "));


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", bdCodeVolumeRepository.findOne(act.getVolume().getId()));
        List<BdCodeVolume> volumeList = bdCodeVolumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        model.addAttribute("act", act);
        model.addAttribute("hl",hl);
        return "website/index";
    }*/

   /* @RequestMapping(value = {"bdcode-act-part-chapter-pdf-{id}.html"}, method = RequestMethod.GET)
    public String bdCodeChpaterPartDetails(Model model,
                                         Locale locale,
                                         @RequestParam(name = "hl", required = false) String hl,
                                         @PathVariable("id") Integer id, HttpServletRequest request) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        BdCodeAct act = bdCodeActRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(id);

        if (act == null) {
            return "redirect:/404.html";
        }

        model.addAttribute("page", "bangladesh-code-act-part-chapter");
        model.addAttribute("metaTitle", act.getShortTitle());
        model.addAttribute("metaDescription", "WHEREAS it is expedient to consolidate the law applicable to intestate and testamentary succession in Bangladesh; it is hereby enacted as follows:-ion"*//*act.getActRole() + act.getPreamble()*//*);
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", "));


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", bdCodeVolumeRepository.findOne(act.getVolume().getId()));
        List<BdCodeVolume> volumeList = bdCodeVolumeRepository.findByStatusTrueOrderByIdAsc();
        List<BdCodeActPart> partList = bdCodeActPartRepository.findAllByStatusTrueOrderByIdAsc(act.getId());
       // List<BdCodeActChapter> chapterList = bdCodeActChapterRepository.findByStatusTrueOrderByIdAsc(act.getId());
        model.addAttribute("volumes", volumeList);
        model.addAttribute("act", act);
        model.addAttribute("parts", partList);
       // model.addAttribute("chapters", chapterList);
        model.addAttribute("hl",hl);
        return "website/index";
    }*/

    @RequestMapping(value = {"act-details-{id}.html"}, method = RequestMethod.GET)
    public String actDetails(Model model,
                             Locale locale,
                             @PathVariable("id") Integer id, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(id);

        if (act == null) {
            return "redirect:/404.html";
        }

        model.addAttribute("page", "act-details");
        model.addAttribute("metaTitle", act.getShortTitle());
        model.addAttribute("metaDescription", act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", "));


        List<ActSection> sections = actSectionRepository.findByActIdAndStatusIsTrueOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(act);

        model.addAttribute("sections", sections);

        model.addAttribute("actShortList", getActShort());

        model.addAttribute("volume", actRepository.findOne(act.getVolume().getId()));
        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        model.addAttribute("act", act);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }

    @RequestMapping(value = {"act-print-{id}.html"}, method = RequestMethod.GET)
    public String actDetailsPrint(Model model,
                                  Locale locale,
                                  @PathVariable("id") Integer id, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(id);
        if (act == null) {
            return "redirect:/404.html";
        }
        //model.addAttribute("page", "act-details-print");
        model.addAttribute("metaTitle", act.getShortTitle());
        model.addAttribute("metaDescription", act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", "));
        List<ActSection> sections = actSectionRepository.findByActIdAndStatusIsTrueOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(act);
        model.addAttribute("sections", sections);
        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", actRepository.findOne(act.getVolume().getId()));
        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        model.addAttribute("act", act);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/pages/act-details-print";
    }


    @RequestMapping(value = {"act-{actId}/part-{partId}.html"}, method = RequestMethod.GET)
    public String actPart(Model model,
                          Locale locale,
                          @PathVariable("actId") Integer actId,
                          @PathVariable("partId") Integer partId, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);

        if (act == null) {
            return "redirect:/404.html";
        }

        ActPart actPart = actPartRepository.findByIdAndStatusIsTrueAndActId_idIs(partId, actId);

        if (actPart == null) {
            return "redirect:/404.html";
        }


        model.addAttribute("actPart", actPart);

        model.addAttribute("page", "act-part");
        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actPart.getPartName());
        model.addAttribute("metaDescription", actPart.getPartName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actPart.getPartName().replaceAll(" ", ","));


        List<ActSection> actSections = actSectionRepository.findByActId_statusIsTrueAndPartId_idIsOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(partId);


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("act", act);

        model.addAttribute("actShortList", getActShort());

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        model.addAttribute("sections", actSections);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"act-{actId}/part-details-{partId}.html"}, method = RequestMethod.GET)
    public String actPartDetails(Model model,
                                 Locale locale,
                                 @PathVariable("actId") Integer actId,
                                 @PathVariable("partId") Integer partId
            , HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);

        if (act == null) {
            return "redirect:/404.html";
        }

        ActPart actPart = actPartRepository.findByIdAndStatusIsTrueAndActId_idIs(partId, actId);

        if (actPart == null) {
            return "redirect:/404.html";
        }


        model.addAttribute("page", "act-part-details");

        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actPart.getPartName());
        model.addAttribute("metaDescription", actPart.getPartName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actPart.getPartName().replaceAll(" ", ","));


        List<ActSection> actSections = actSectionRepository.findByActId_statusIsTrueAndPartId_idIsOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(partId);


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("act", act);

        model.addAttribute("actShortList", getActShort());

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        model.addAttribute("sections", actSections);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");

        return "website/index";
    }
    @RequestMapping(value = {"act-{actId}/act-part-print-{partId}.html"}, method = RequestMethod.GET)
    public String actPartPrint(Model model,
                               Locale locale,
                               @PathVariable("actId") Integer actId,
                               @PathVariable("partId") Integer partId
            , HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);

        if (act == null) {
            return "redirect:/404.html";
        }

        ActPart actPart = actPartRepository.findByIdAndStatusIsTrueAndActId_idIs(partId, actId);

        if (actPart == null) {
            return "redirect:/404.html";
        }



        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actPart.getPartName());
        model.addAttribute("metaDescription", actPart.getPartName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actPart.getPartName().replaceAll(" ", ","));


        List<ActSection> actSections = actSectionRepository.findByActId_statusIsTrueAndPartId_idIsOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(partId);


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("act", act);

        model.addAttribute("actShortList", getActShort());

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        model.addAttribute("sections", actSections);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");

        return "website/pages/act-part-details-print";
    }


    @RequestMapping(value = {"act-{actId}/chapter-{chapterId}.html"}, method = RequestMethod.GET)
    public String actChapter(Model model,
                             Locale locale,
                             @PathVariable("actId") Integer actId,
                             @PathVariable("chapterId") Integer chapterId
            , HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);

        if (act == null) {
            return "redirect:/404.html";
        }

        ActChapter actChapter = actChapterRepository.findByIdAndStatusIsTrueAndActId_idIs(chapterId, actId);

        if (actChapter == null) {
            return "redirect:/404.html";
        }


        model.addAttribute("actChapter", actChapter);
        model.addAttribute("page", "act-chapter");

        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actChapter.getChapterName());
        model.addAttribute("metaDescription", actChapter.getChapterName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actChapter.getChapterName().replaceAll(" ", ","));


        List<ActSection> actSections = actSectionRepository.findByActId_statusIsTrueAndChapterId_idIsOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(chapterId);


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("act", act);

        model.addAttribute("actShortList", getActShort());

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        model.addAttribute("sections", actSections);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"act-{actId}/chapter-details-{chapterId}.html"}, method = RequestMethod.GET)
    public String actChapterDetails(Model model,
                                    Locale locale,
                                    @PathVariable("actId") Integer actId,
                                    @PathVariable("chapterId") Integer chapterId
            , HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);

        if (act == null) {
            return "redirect:/404.html";
        }

        ActChapter actChapter = actChapterRepository.findByIdAndStatusIsTrueAndActId_idIs(chapterId, actId);

        if (actChapter == null) {
            return "redirect:/404.html";
        }

        model.addAttribute("page", "act-chapter-details");

        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actChapter.getChapterName());
        model.addAttribute("metaDescription", actChapter.getChapterName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actChapter.getChapterName().replaceAll(" ", ","));


        List<ActSection> actSections = actSectionRepository.findByActId_statusIsTrueAndChapterId_idIsOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(chapterId);


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("act", act);

        model.addAttribute("actShortList", getActShort());

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        model.addAttribute("sections", actSections);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }

    @RequestMapping(value = {"act-{actId}/act-chapter-print-{chapterId}.html"}, method = RequestMethod.GET)
    public String actChapterPrint(Model model,
                                  Locale locale,
                                  @PathVariable("actId") Integer actId,
                                  @PathVariable("chapterId") Integer chapterId
            , HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());


        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);

        if (act == null) {
            return "redirect:/404.html";
        }

        ActChapter actChapter = actChapterRepository.findByIdAndStatusIsTrueAndActId_idIs(chapterId, actId);

        if (actChapter == null) {
            return "redirect:/404.html";
        }

        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actChapter.getChapterName());
        model.addAttribute("metaDescription", actChapter.getChapterName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actChapter.getChapterName().replaceAll(" ", ","));


        List<ActSection> actSections = actSectionRepository.findByActId_statusIsTrueAndChapterId_idIsOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(chapterId);


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("act", act);

        model.addAttribute("actShortList", getActShort());

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);

        model.addAttribute("sections", actSections);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/pages/act-chapter-details-print";
    }


    @RequestMapping(value = {"act-{actId}/section-{sectionId}.html"}, method = RequestMethod.GET)
    public String actSectionDetails(Model model,
                                    Locale locale,
                                    @PathVariable("actId") Integer actId,
                                    @RequestParam(name = "hl", required = false) String hl,
                                    @PathVariable("sectionId") Integer sectionId
            , HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);

        model.addAttribute("totalHits", hitRepository.count());


        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);

        if (act == null) {
            return "redirect:/404.html";
        }


        ActSection actSection = actSectionRepository.findByIdAndStatusIsTrueAndActId_idIs(sectionId, actId);

        if (actSection == null) {
            return "redirect:/404.html";
        }
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("page", "act-section-details");

        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actSection.getSectionName());
        model.addAttribute("metaDescription", actSection.getSectionName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actSection.getSectionName().replaceAll(" ", ","));


        List<ActSection> actSections = new ArrayList<>();
        actSections.add(actSection);


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("act", act);

        model.addAttribute("actShortList", getActShort());

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        model.addAttribute("section", actSection);
        model.addAttribute("sections", actSections);
        model.addAttribute("hl",hl);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }
    @RequestMapping(value = {"act-print-{actId}/section-print-{sectionId}.html"}, method = RequestMethod.GET)
    public String actSectionPrint(Model model,
                                    Locale locale,
                                    @PathVariable("actId") Integer actId,
                                    @RequestParam(name = "hl", required = false) String hl,
                                    @PathVariable("sectionId") Integer sectionId
            , HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);

        model.addAttribute("totalHits", hitRepository.count());


        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);

        if (act == null) {
            return "redirect:/404.html";
        }


        ActSection actSection = actSectionRepository.findByIdAndStatusIsTrueAndActId_idIs(sectionId, actId);

        if (actSection == null) {
            return "redirect:/404.html";
        }
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        //model.addAttribute("page", "act-section-details");

        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actSection.getSectionName());
        model.addAttribute("metaDescription", actSection.getSectionName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actSection.getSectionName().replaceAll(" ", ","));


        List<ActSection> actSections = new ArrayList<>();
        actSections.add(actSection);


        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));

        model.addAttribute("act", act);

        model.addAttribute("actShortList", getActShort());

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        model.addAttribute("sections", actSections);
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("hl",hl);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/pages/act-section-print";
    }

/*    @RequestMapping(value = {"act-print-{actId}/section-print-{sectionId}.html"}, method = RequestMethod.GET)
    public String actSectionPrint(Model model,
                                  Locale locale,
                                  @PathVariable("actId") Integer actId,
                                  @RequestParam(name = "hl", required = false) String hl,
                                  @PathVariable("sectionId") Integer sectionId
            , HttpServletRequest request) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        Act act = actRepository.findByIdAndVolume_statusIsTrueAndStatusIsTrue(actId);
        if (act == null) {
            return "redirect:/404.html";
        }
        ActSection actSection = actSectionRepository.findByIdAndStatusIsTrueAndActId_idIs(sectionId, actId);
        if (actSection == null) {
            return "redirect:/404.html";
        }
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));
        model.addAttribute("metaTitle", act.getShortTitle() + " | " + actSection.getSectionName());
        model.addAttribute("metaDescription", actSection.getSectionName() + " | " + act.getActRole() + act.getPreamble());
        model.addAttribute("metaKeywords", act.getShortTitle().replaceAll(" ", ", ") + " , " + actSection.getSectionName().replaceAll(" ", ","));
        List<ActSection> actSections = new ArrayList<>();
        actSections.add(actSection);
        model.addAttribute("actShortList", getActShort());
        model.addAttribute("volume", volumeRepository.findOne(act.getVolume().getId()));
        model.addAttribute("act", act);
        model.addAttribute("actShortList", getActShort());
        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        model.addAttribute("sections", actSections);
        model.addAttribute("hl",hl);
        return "website/pages/act-section-print";
    }*/

    @RequestMapping(value = {"search.html"}, method = RequestMethod.GET)
    public String search(Model model,
                         @RequestParam(name = "q", required = false) String q,
                         @RequestParam(name = "type", required = false) Integer type,
                         @RequestParam(name = "page", required = false) Integer page,
                         @Valid @ModelAttribute("search") AdvancedSearchForm search,
                         BindingResult result,
                         Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());

        if (q != null) {
            q = Jsoup.parse(q).text();
        }
        Integer size = 20;

        Page<?> results = null;

        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1;
        }

        Map<Integer, String> types = getTypes();


        Pageable pageable = new PageRequest(page, size);
        if(search.getVolume() == null && q == null && types.get(type) == null && search.getActNo()==null && search.getYear()==null && search.getChapterNo()==null && search.getPartNo()==null)
        {

        }
       else if(search.getVolume() == null && search.getYear()!=null && search.getActNo()==null  && search.getPartNo()==null && search.getChapterNo()==null && search.getSectionNo()==null && q == null && types.get(type) == null)
        {

                    if (search.getYear() != null) {
                       /* predicates.add(cb.equal(root.get("actId").get("year"), search.getYear()));
                        */
                        results = actSearchRepository.findByActId_yearContainingIgnoreCaseOrActId_yearContainingIgnoreCase(search.getYear(), search.getYear(), pageable);
                    }

        }
        else if(search.getVolume() == null && search.getYear()!=null && search.getActNo()!=null  && search.getPartNo()==null && search.getChapterNo()==null && search.getSectionNo()==null && q == null && types.get(type) == null)
        {

            Specification<AdvancedSearch> searchSpecification = new Specification<AdvancedSearch>() {
                @Override
                public Predicate toPredicate(Root<AdvancedSearch> root,
                                             CriteriaQuery<?> query,
                                             CriteriaBuilder cb) {

                    List<Predicate> predicates = new ArrayList<>();

                    // If designation is specified in filter, add equal where clause
         /*           if (search.getVolume() != null && search.getVolume() > 0) {
                        predicates.add(cb.equal(root.get("actId").get("volume").get("id"), search.getVolume()));
                    }*/

                    // If firstName is specified in filter, add contains (lile)
                    // filter to where clause with ignore case
                    if (search.getYear() != null) {
                        predicates.add(cb.equal(root.get("actId").get("year"), search.getYear()));

                    }
                    if (search.getActNo() != null) {
                        predicates.add(cb.equal(root.get("actId").get("number"), search.getActNo()));

                    }


                    return cb.and(predicates.toArray(new Predicate[0]));

                }
            };
            System.out.println("speci"+searchSpecification);
            results = advancedSearchRepository.findAll(searchSpecification, pageable);

        }
        else if (search.getVolume() == null) {


            if (q != null && q.trim() != "") {
                System.out.println(q.trim());
                q = Jsoup.parse(q).text();
                if (types.get(type) != null) {
                    String typeName = types.get(type);
                    logger.info("Type = " + typeName);
                    String q1 = toBangla(q);
                    String q2 = toEnglish(q);

                    switch (typeName) {
                        case "shortTitle":
                            results = actSearchRepository.findByActId_shortTitleContainingIgnoreCase(q, pageable);
                            break;
                        case "actNo":
                            results = actSearchRepository.findByActId_numberIgnoreCaseOrActId_numberIgnoreCase(q1, q1, pageable);
                            break;
                        case "actYear":
                            results = actSearchRepository.findByActId_yearContainingIgnoreCaseOrActId_yearContainingIgnoreCase(q1, q1, pageable);
                            System.out.println(typeName);
                            break;
                        case "part":
                            results = partSearchRepository.findByTitleContainingIgnoreCaseOrTitleContainingIgnoreCase(q1, q2, pageable);
                            break;
                        case "chapter":
                            results = chapterSearchRepository.findByTitleContainingIgnoreCaseOrTitleContainingIgnoreCase(q1, q2, pageable);
                            break;
                        case "section":
                            results = sectionSearchRepository.findByTitleContainingIgnoreCaseOrTitleContainingIgnoreCase(q1, q2, pageable);
                            break;
                        default:
                            results = searchRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q, pageable);
                            break;
                    }
                } else {

                    type = 1;
                    results = searchRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q, pageable);

                }
            }

        }
        else {
            Specification<AdvancedSearch> searchSpecification = new Specification<AdvancedSearch>() {
                @Override
                public Predicate toPredicate(Root<AdvancedSearch> root,
                                             CriteriaQuery<?> query,
                                             CriteriaBuilder cb) {

                    List<Predicate> predicates = new ArrayList<>();

                    // If designation is specified in filter, add equal where clause
         /*           if (search.getVolume() != null && search.getVolume() > 0) {
                        predicates.add(cb.equal(root.get("actId").get("volume").get("id"), search.getVolume()));
                    }*/

                    // If firstName is specified in filter, add contains (lile)
                    // filter to where clause with ignore case
                    if (search.getYear() != null) {
                        predicates.add(cb.equal(root.get("actId").get("year"), search.getYear()));
                    }
                    if (search.getActNo() != null) {
                        predicates.add(cb.equal(root.get("actId").get("number"), search.getActNo()));
                    }

                    if (search.getPartNo() != null) {
                        predicates.add(cb.equal(root.get("partId").get("partNo"), search.getPartNo()));
                    }
                    if (search.getChapterNo() != null) {
                        predicates.add(cb.equal(root.get("chapterId").get("chapterNo"), search.getChapterNo()));
                    }

                    if (search.getSectionNo() != null) {
                        predicates.add(cb.equal(root.get("sectionId").get("sectionNo"), search.getSectionNo()));
                    }


                    return cb.and(predicates.toArray(new Predicate[0]));

                }
            };
            System.out.println("speci"+searchSpecification);
            results = advancedSearchRepository.findAll(searchSpecification, pageable);

        }


        model.addAttribute("type", type);

        model.addAttribute("q", q);
        model.addAttribute("p", page + 1);
        model.addAttribute("s", size);

        model.addAttribute("results", results);

        model.addAttribute("page", "search");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        List<Volume> volumeList = volumeRepository.findByStatusTrueOrderByIdAsc();
        model.addAttribute("volumes", volumeList);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"news-and-notice.html"}, method = RequestMethod.GET)
    public String newsAndNotice(Model model, Locale locale, @RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());

        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1;
        }

        model.addAttribute("page", "news-and-notice");
        Pageable pageable = new PageRequest(page, 20);
        Page<News> newsList = newsRepository.findByStatusIsTrueOrderByCreatedAt(pageable);

        model.addAttribute("newsList", newsList);

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }

    @RequestMapping(value = {"news-and-notice/{id}.html"}, method = RequestMethod.GET)
    public String newsDetails(Model model, Locale locale, @PathVariable("id") Integer id, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());

        News news = newsRepository.findByIdAndStatusIsTrue(id);
        if (news == null) {
            return "redirect:/news-and-notice.html";
        }


        if (locale.getLanguage() == "en") {
            model.addAttribute("metaTitle", news.getTitleEnglish());
            model.addAttribute("metaDescription", news.getTitleEnglish());
            model.addAttribute("metaKeywords", news.getTitleEnglish().replaceAll(" ", ", "));
        } else {
            model.addAttribute("metaTitle", news.getTitleBangla());
            model.addAttribute("metaDescription", news.getTitleBangla());
            model.addAttribute("metaKeywords", news.getTitleBangla().replaceAll(" ", ", "));
        }

        model.addAttribute("news", news);
        model.addAttribute("page", "news-details");
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }

    @RequestMapping(value = {"related-links.html"}, method = RequestMethod.GET)
    public String relatedLinks(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());

        List<RelatedLink> relatedLinks = relatedLinkRepository.findByStatusIsTrueOrderByIdAsc();

        model.addAttribute("page", "related-links");
        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        model.addAttribute("relatedLinks", relatedLinks);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"site-map.html", "sitemap.html"}, method = RequestMethod.GET)
    public String siteMap(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        model.addAttribute("page", "sitemap");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"contact-us.html", "contactus.html"}, method = RequestMethod.GET)
    public String contactUs(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        FeedBack feedBack = new FeedBack();
        model.addAttribute("feedBack", feedBack);
        model.addAttribute("page", "contact-us");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }
    @RequestMapping(value = {"feedback-suggestion.html", "feedback-suggestion.html"}, method = RequestMethod.GET)
    public String feedbacksuggestion(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        FeedBack feedBack = new FeedBack();
        model.addAttribute("feedBack", feedBack);
        model.addAttribute("page", "contact-us");
        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }



    @RequestMapping(value = {"contact-us.html", "contactus.html"}, method = RequestMethod.POST)
    public String saveContactUs(Model model, Locale locale, @Valid @ModelAttribute("feedBack") FeedBack feedBack, BindingResult result, HttpServletRequest request, final HttpServletResponse response
            , @RequestParam(value = "g-recaptcha-response", required = false) String gCaptchaResponse) {
        hitLogger(request);
        if (!verifyRecaptcha.verify(gCaptchaResponse)) {
            return "redirect:/login?captcha";
        }
        model.addAttribute("totalHits", hitRepository.count());

        feedBackValidator.validate(feedBack, result);
        if (result.hasErrors()) {
            return "redirect:/contact-us.html?error";
        }

        feedBack.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        feedBack.setCreatedAt(new Date());
        feedBackRepository.save(feedBack);
        model.addAttribute("page","thank-you");
        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    @RequestMapping(value = {"faq.html"}, method = RequestMethod.GET)
    public String faq(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());

        List<Faq> faqList = faqRepository.findByStatusIsTrueOrderByIdAsc();

        model.addAttribute("page", "faq");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        model.addAttribute("faqList", faqList);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }

    @RequestMapping(value = {"how-to-print.html"}, method = RequestMethod.GET)
    public String howToPrint(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        StaticPage staticPage = staticPageRepository.findByPageUrl("how-to-print.html");

        model.addAttribute("page", "static-page");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        model.addAttribute("staticPage", staticPage);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }

    @RequestMapping(value = {"how-to-search.html"}, method = RequestMethod.GET)
    public String howToSearch(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        StaticPage staticPage = staticPageRepository.findByPageUrl("how-to-search.html");

        model.addAttribute("page", "static-page");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        model.addAttribute("staticPage", staticPage);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }

    @RequestMapping(value = {"glossary.html"}, method = RequestMethod.GET)
    public String glossary(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        StaticPage staticPage = staticPageRepository.findByPageUrl("glossary.html");

        model.addAttribute("page", "static-page");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        model.addAttribute("staticPage", staticPage);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }

    @RequestMapping(value = {"roman-number.html"}, method = RequestMethod.GET)
    public String romanNumber(Model model, Locale locale, HttpServletRequest request, final HttpServletResponse response) {
        hitLogger(request);
        model.addAttribute("totalHits", hitRepository.count());
        StaticPage staticPage = staticPageRepository.findByPageUrl("roman-number.html");

        model.addAttribute("page", "static-page");

        model.addAttribute("metaTitle", messageSource.getMessage("website.meta.title", null, locale));
        model.addAttribute("metaDescription", messageSource.getMessage("website.meta.description", null, locale));
        model.addAttribute("metaKeywords", messageSource.getMessage("website.meta.keywords", null, locale));

        model.addAttribute("staticPage", staticPage);
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "3600");
        return "website/index";
    }


    public Map<String, Integer> getActShort() {
        List<Object[]> actShortList = actRepository.findActShortTitle();
        Map<String, Integer> output = new HashMap<>();
        for (Object[] actShort : actShortList) {
            output.put((String) actShort[1], (Integer) actShort[0]);
        }
        return output;
    }

    public String toBangla(String input) {
        return input.replaceAll("0", "০")
                .replaceAll("1", "১")
                .replaceAll("2", "২")
                .replaceAll("3", "৩")
                .replaceAll("4", "৪")
                .replaceAll("5", "৫")
                .replaceAll("6", "৬")
                .replaceAll("7", "৭")
                .replaceAll("8", "৮")
                .replaceAll("9", "৯");
    }

    public String toEnglish(String input) {
        return input.replaceAll("0", "০")
                .replaceAll("১", "1")
                .replaceAll("২", "2")
                .replaceAll("৩", "3")
                .replaceAll("৪", "4")
                .replaceAll("৫", "5")
                .replaceAll("৬", "6")
                .replaceAll("৭", "7")
                .replaceAll("৮", "8")
                .replaceAll("৯", "9");
    }

    public Map<Integer, String> getTypes() {
        Map<Integer, String> types = new HashMap<>();
        types.put(1, null);
        types.put(2, "shortTitle");
        types.put(3, "actNo");
        types.put(4, "actYear");
        types.put(5, "part");
        types.put(6, "chapter");
        types.put(7, "section");
        return types;
    }
}
