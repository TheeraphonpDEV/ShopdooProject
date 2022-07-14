package com.shopdoo.admin.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopdoo.admin.FileUploadUtil;
import com.shopdoo.admin.security.ShopdooUserDetails;
import com.shopdoo.common.entity.User;

@Controller
	public class AccountController {

		@Autowired
		private UserService service;

		@GetMapping("/account")
		public String viewDetails(@AuthenticationPrincipal ShopdooUserDetails loggedUser,
				Model model) {
			
			String email = loggedUser.getUsername();
			
			User user = service.getByEmail(email);
			
			model.addAttribute("user", user);
			
			return "account_form";
		}
		
		@PostMapping("/account/update")
		public String saveDetails(User user, RedirectAttributes redirectAttributes,
				@AuthenticationPrincipal ShopdooUserDetails loggedUser,
				@RequestParam("image") MultipartFile multipartFile) throws IOException {
			
			if  (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				user.setPhotos(fileName);
				User savedUser = service.updateAccount(user);
				
				String uploadDir = "user-photos/" + savedUser.getId();
				
				FileUploadUtil.cleanDir(uploadDir);
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
				
			} else {
				if (user.getPhotos().isEmpty()) user.setPhotos(null);
				service.updateAccount(user);
			}
			
			loggedUser.setfirstName(user.getFirstName());
			loggedUser.setlastName(user.getLastName());
			
			redirectAttributes.addFlashAttribute("message", "The account details have been updated. ^^");
			
			return "redirect:/account";
		}
}
