package com.it.bo.server.views;


import com.it.bo.server.controllers.MainViewController;
import com.it.server.SecurityConfig;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A responsive menu component providing user information and the controls for
 * primary navigation between the views.
 */
public final class AdminMenu extends CustomComponent {

	private static final long serialVersionUID = 3531006337900409016L;
	
	private MainViewController eventsHandler;
	
	
	public static final String ID = "dashboard-menu";
    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
    private static final String STYLE_VISIBLE = "valo-menu-visible";

    public AdminMenu(MainViewController eventsHandler) {
    	this.eventsHandler = eventsHandler;
        setPrimaryStyleName("valo-menu");
        setId(ID);
        setSizeUndefined();

        setCompositionRoot(buildContent());
    }
    
    

    private Component buildContent() {
        final CssLayout menuContent = new CssLayout();
        menuContent.addStyleName("sidebar");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.addStyleName("no-vertical-drag-hints");
        menuContent.addStyleName("no-horizontal-drag-hints");
        menuContent.setWidth(null);
        menuContent.setHeight("100%");

        menuContent.addComponent(buildTitle());
        menuContent.addComponent(buildUserMenu());
        menuContent.addComponent(buildToggleButton());
        menuContent.addComponent(buildMenuItems());

        return menuContent;
    }


	private Component buildTitle() {
        Label logo = new Label("Welcome <strong>Admin</strong>", ContentMode.HTML);
        logo.setSizeUndefined();
        VerticalLayout logoWrapper = new VerticalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        
        return logoWrapper;
    }
	
	 private Component buildUserMenu() {
	        MenuBar settings = new MenuBar();
	        settings.addStyleName("user-menu");
	        MenuItem settingsItem = settings.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);
	        settingsItem.addItem("Sign Out", selectedItem -> UI.getCurrent().getPage().setLocation(SecurityConfig.SIGN_OUT_URL));
	        return settings;
	    }

    private Component buildToggleButton() {
        Button valoMenuToggleButton = new Button("Menu", new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(final ClickEvent event) {
                if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
                    getCompositionRoot().removeStyleName(STYLE_VISIBLE);
                } else {
                    getCompositionRoot().addStyleName(STYLE_VISIBLE);
                }
            }
        });
        valoMenuToggleButton.setIcon(FontAwesome.LIST);
        valoMenuToggleButton.addStyleName("valo-menu-toggle");
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
        return valoMenuToggleButton;
    }

	private Component buildMenuItems() {
		CssLayout menuItemsLayout = new CssLayout();
		menuItemsLayout.addStyleName("valo-menuitems");
		
		for(AdminMenuItem item : AdminMenuItem.values()) {
			menuItemsLayout.addComponent(buildMenuItemButton(item));
		}
		return menuItemsLayout;

	}


    private Button buildMenuItemButton(final AdminMenuItem menuItem) {
    	Button menuItemBtn = new Button();
    	menuItemBtn.setPrimaryStyleName("valo-menu-item");
    	menuItemBtn.setIcon(menuItem.getIcon());
    	menuItemBtn.setCaption(menuItem.getViewName());
    	
    	menuItemBtn.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 566296333816948975L;

			@Override
			public void buttonClick(ClickEvent event) {
				eventsHandler.navigateToView(menuItem);
				
			}
		});
    	
    	
		return menuItemBtn;
	}

}
