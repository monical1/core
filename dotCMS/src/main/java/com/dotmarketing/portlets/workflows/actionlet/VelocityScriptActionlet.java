package com.dotmarketing.portlets.workflows.actionlet;

import com.dotcms.api.web.HttpServletRequestThreadLocal;
import com.dotcms.api.web.HttpServletResponseThreadLocal;
import com.dotcms.rendering.engine.ScriptEngine;
import com.dotcms.rendering.engine.ScriptEngineFactory;
import com.dotcms.util.CollectionsUtils;
import com.dotmarketing.portlets.workflows.model.WorkflowActionClassParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowActionFailureException;
import com.dotmarketing.portlets.workflows.model.WorkflowActionletParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowProcessor;
import com.dotmarketing.util.Logger;
import com.google.common.collect.ImmutableList;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

/**
 * Velocity Script Actionlet allows to execute custom script in a workflow action
 * @author jsanca
 */
public class VelocityScriptActionlet extends WorkFlowActionlet {

    private final static String ENGINE = "Velocity";
    private static List<WorkflowActionletParameter> parameterList = createParamList();

    private static List<WorkflowActionletParameter> createParamList () {

        final ImmutableList.Builder<WorkflowActionletParameter> paramList = new ImmutableList.Builder<>();

        paramList.add(new WorkflowActionletParameter
                ("script", "Script Code", null, false));
        paramList.add(new WorkflowActionletParameter
                ("resultKey", "Result key", "result", false));

        return paramList.build();
    }

    @Override
    public List<WorkflowActionletParameter> getParameters() {

        return parameterList;
    }

    @Override
    public String getName() {
        return "Velocity Script Actionlet";
    }

    @Override
    public String getHowTo() {

        return "This actionlet give the ability to run a velocity script as part of the workflow action.";
    }

    @Override
    public void executeAction(final WorkflowProcessor processor,
                              final Map<String, WorkflowActionClassParameter> params) throws WorkflowActionFailureException {

        try {

            final WorkflowActionClassParameter scriptParameter = params.get("script");
            final WorkflowActionClassParameter keyParameter    = params.get("resultKey");
            final ScriptEngine engine = ScriptEngineFactory.getInstance().getEngine(ENGINE);
            final String script       = scriptParameter.getValue();
            final String resultKey    = keyParameter.getValue();
            final Reader reader       = new StringReader(script);
            final Object result       = engine.eval(HttpServletRequestThreadLocal.INSTANCE.getRequest(),
                    HttpServletResponseThreadLocal.INSTANCE.getResponse(), reader,
                    CollectionsUtils.map("workflow", processor,
                            "user", processor.getUser(),
                            "contentlet", processor.getContentlet(),
                            "content", processor.getContentlet()));

            if (null != result && null != resultKey) {
                processor.getContentlet().setProperty(resultKey, result);
            }
        } catch (Exception e) {

            Logger.error(this, e.getMessage(), e);
            throw new WorkflowActionFailureException(e.getMessage(), e);
        }
    }
}