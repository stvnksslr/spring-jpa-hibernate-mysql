package com.skessler.controller;

import java.util.List;
import javax.validation.Valid;

import com.skessler.exception.OrderNotFound;
import com.skessler.model.Acsorders;
import com.skessler.service.OrderService;
import com.skessler.validation.OrderValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value="/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderValidator orderValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(orderValidator);
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public ModelAndView newOrderPage() {
        ModelAndView mav = new ModelAndView("order-new", "order", new Acsorders());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewOrder(@ModelAttribute @Valid Acsorders acsorders,
                                      BindingResult result,
                                      final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return new ModelAndView("order-new");

        ModelAndView mav = new ModelAndView();
        String message = "New order "+acsorders.getOrderdata()+" was successfully created.";

        orderService.create(acsorders);
        mav.setViewName("redirect:/index.html");

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView orderListPage() {
        ModelAndView mav = new ModelAndView("order-list");
        List<Acsorders> acsordersList = orderService.findAll();
        mav.addObject("acsordersList", acsordersList);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editOrderPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("order-edit");
        Acsorders acsorders = orderService.findById(id);
        mav.addObject("acsorders", acsorders);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editOrder(@ModelAttribute @Valid Acsorders acsorders,
                                 BindingResult result,
                                 @PathVariable Integer id,
                                 final RedirectAttributes redirectAttributes) throws OrderNotFound {

        if (result.hasErrors())
            return new ModelAndView("order-edit");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "Order was successfully updated.";

        orderService.update(acsorders);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable Integer id,
                                   final RedirectAttributes redirectAttributes) throws OrderNotFound {

        ModelAndView mav = new ModelAndView("redirect:/index.html");

        Acsorders acsorders = orderService.delete(id);
        String message = "The order "+acsorders.getOrderdata()+" was successfully deleted.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

}
