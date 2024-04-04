package com.training.aem.core.servlets;

import com.training.aem.core.services.NodeCreationService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.settings.SlingSettingsService;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class AddPropertyServletTest {
    AemContext aemContext = new AemContext(ResourceResolverType.JCR_MOCK);
    @Mock
    SlingHttpServletResponse response;
    @Mock
    SlingHttpServletRequest request;
    @InjectMocks
    AddPropertyServlet addPropertyServlet;
    @Mock
    ResourceResolver resourceResolver;
    @Mock
    ResourceResolverFactory resourceResolverFactory;
    @Mock
    Resource resource;
    @Mock
    ModifiableValueMap valueMap;
    @Mock
    NodeCreationService nodeCreationService;
    @Mock
    PrintWriter printWriter;
    @Mock
    SlingSettingsService slingSettingsService;

    @BeforeEach
    void setUp() {
        when(slingSettingsService.getRunModes()).thenReturn(new HashSet<>(Arrays.asList("author")));
    }

    @Test
    void doGet() throws ServletException, IOException, LoginException {
        when(request.getParameter("jcrPath")).thenReturn("/content/training-project/node_1710250426191");
//        when(slingSettingsService.getRunModes()).thenReturn(new HashSet<>(Arrays.asList("author")));
        when(request.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.getResource("/content/training-project/node_1710250426191")).thenReturn(resource);
        when(resource.adaptTo(ModifiableValueMap.class)).thenReturn(valueMap);
        when(response.getWriter()).thenReturn(printWriter);
        addPropertyServlet.doGet(request,response);
    }

    @Test
    void testDoGetWithNonExistingResource() throws ServletException, IOException {
        when(request.getParameter("jcrPath")).thenReturn("/content/training-project/node_1710250426191");
        when(request.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.getResource("/content/training-project/node_1710250426191")).thenReturn(resource);
        when(resource.getResourceType()).thenReturn("sling:nonexisting");
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        addPropertyServlet.doGet(request, response);
        verify(response).setStatus(SlingHttpServletResponse.SC_NOT_FOUND);

    }

    @Test
    void testDoGetWithNullValueMap() throws ServletException, IOException {
        when(request.getParameter("jcrPath")).thenReturn("/content/training-project/node_1710250426191");
        when(request.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.getResource("/content/training-project/node_1710250426191")).thenReturn(resource);
        when(resource.adaptTo(ModifiableValueMap.class)).thenReturn(null);
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        addPropertyServlet.doGet(request, response);
        verify(response).setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        verify(writer).write("Failed to adapt Resource to ModifiableValueMap");
    }

    @Test
    void testDoGetWithEmptyPath() throws ServletException, IOException {
        when(request.getParameter("jcrPath")).thenReturn("");
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        addPropertyServlet.doGet(request, response);
        verify(response).setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
        verify(writer).write("Invalid request parameters");
        verifyNoInteractions(resourceResolver, resource, valueMap);
    }

    @Test
    public void testDoGetWithNonAuthorRunMode() throws ServletException, IOException {
        when(slingSettingsService.getRunModes()).thenReturn(new HashSet<>(Arrays.asList("publish")));
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        addPropertyServlet.doGet(request, response);
        verify(response).setStatus(SlingHttpServletResponse.SC_FORBIDDEN);
        verify(response.getWriter()).write("This servlet can only be accessed in author run mode");
    }


}