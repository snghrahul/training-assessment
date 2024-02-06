//package com.training.aem.core.services.impl;
//
//import com.training.aem.core.models.Faq;
//import com.training.aem.core.services.FaqService;
//import org.osgi.service.component.annotations.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component(service = FaqService.class, immediate = true)
//public class FaqServiceImpl  implements FaqService{
//
//    private List<Faq> faqs = new ArrayList<>();
//
//
//
//    @Override
//    public void addFaq(String question, String answer) {
//        Faq faq = new Faq(question,answer);
//        faqs.add(faq);
//
//    }
//
//    @Override
//    public List<Faq> getFaqs() {
//        return faqs;
//    }
//}
