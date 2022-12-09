package org.larina.application.controller;

import org.larina.application.entities.*;
import org.larina.application.repo.CompanyRepo;
import org.larina.application.repo.LinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private LinkRepo linkRepo;

    @GetMapping("/company")
    public String CompanyList(@RequestParam(required = false) String name, Model model){

        Iterable<Link> linkList = null;
        List<CompanyAndLink> companyAndLinkList = null;
        CompanyAndLink companyAndLink = null;

        if(name != null && !name.isEmpty()){
                linkList = linkRepo.findAll();
                companyAndLinkList = new ArrayList<CompanyAndLink>();
                for (Link i : linkList) {
                    if (companyRepo.findCompanyByNameAndLinkId(name, i.getId()) != null) {
                        companyAndLink = new CompanyAndLink(companyRepo.findCompanyByNameAndLinkId(name, i.getId()), i);
                        companyAndLinkList.add(companyAndLink);
                    }
                }
                model.addAttribute("companyAndLinkList",companyAndLinkList);
                model.addAttribute("name", name);
        }else {
            model.addAttribute("companyAndLinkList",allCompanyTable());
        }
        return "companyTable";
    }

    @PostMapping("/company")//RequestParam берет значения из формочки (post)
    public String addNewCompany(@Valid Company company,
                                @Valid Link link,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("company", company);
        } else {
            model.addAttribute("company", null);
            linkRepo.save(link);
            company.setLink(link);
            companyRepo.save(company);
        }

        model.addAttribute("companyAndLinkList", allCompanyTable());

        return "companyTable";
    }

    public List<CompanyAndLink> allCompanyTable(){

        Iterable<Link> linkList = null;
        List<CompanyAndLink> companyAndLinkList = null;
        CompanyAndLink companyAndLink = null;

        linkList = linkRepo.findAll();

        companyAndLinkList = new ArrayList<CompanyAndLink>();

        for (Link i : linkList) {
            if(companyRepo.findByLinkId(i.getId()) != null) {
                companyAndLink = new CompanyAndLink(companyRepo.findByLinkId(i.getId()), i);
                companyAndLinkList.add(companyAndLink);
            }
        }

        return companyAndLinkList;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    @GetMapping("/companyDelete")
    public String deleteCompany(@RequestParam("companyId")
                                Long companyId) throws IOException {
            companyRepo.delete(companyRepo.findCompanyById(companyId));
            linkRepo.delete(companyRepo.findCompanyById(companyId).getLink());
        return "redirect:/company";
    }

}
