package edu.uark.registerapp.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.ProductsQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.enums.EmployeeClassification;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionRouteController extends BaseRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showTransaction(final HttpServletRequest request)
	{
			ModelAndView modelAndView = new ModelAndView(ViewNames.PRODUCT_LISTING.getViewName());

			final Optional<ActiveUserEntity> activeUserEntity = this.getCurrentUser(request);
			if(!activeUserEntity.isPresent())
			{
					return buildInvalidSessionResponse();
			}
			return modelAndView;
	}
}
