package net.hillsdon.reviki.jira.renderer;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;

import net.hillsdon.reviki.vc.PageReference;
import net.hillsdon.reviki.wiki.renderer.creole.LinkParts;
import net.hillsdon.reviki.wiki.renderer.creole.LinkPartsHandler;
import net.hillsdon.reviki.wiki.renderer.creole.LinkResolutionContext;
import net.hillsdon.reviki.wiki.renderer.creole.SimpleLinkHandler;

public class JiraLinkHandler extends SimpleLinkHandler implements LinkPartsHandler {

  public JiraLinkHandler(String fmat, LinkResolutionContext context) {
    super(fmat, context);
  }

  @Override
  public boolean isAcronymNotLink(PageReference page, LinkParts parts) {
    IssueService issueService = ComponentAccessor.getIssueService();
    // We could get an IssueManager from the ComponentAccessor, then we can simply
    //call isExistingIssueKey but that is fairly new and marked experimental
    return !issueService.getIssue(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(), parts.getText()).isValid();
  }
}
