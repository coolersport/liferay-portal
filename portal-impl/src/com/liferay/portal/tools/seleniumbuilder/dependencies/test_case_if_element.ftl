<#if ifElement.getName() == "elseif">
	<#assign ifType = "else if">
<#elseif ifElement.getName() == "while">
	<#assign ifType = "while">
<#else>
	<#assign ifType = "if">
</#if>

${ifType} (
	<#if ifElement.element("and")??>
		<#assign conditionalElement = ifElement.element("and")>
	<#elseif ifElement.element("condition")??>
		<#assign conditionalElement = ifElement.element("condition")>
	<#elseif ifElement.element("contains")??>
		<#assign conditionalElement = ifElement.element("contains")>
	<#elseif ifElement.element("equals")??>
		<#assign conditionalElement = ifElement.element("equals")>
	<#elseif ifElement.element("isset")??>
		<#assign conditionalElement = ifElement.element("isset")>
	<#elseif ifElement.element("not")??>
		<#assign conditionalElement = ifElement.element("not")>
	<#elseif ifElement.element("or")??>
		<#assign conditionalElement = ifElement.element("or")>
	</#if>

	<#assign ifConditionalElement = conditionalElement>

	<#include "test_case_if_conditional_element.ftl">
) {
	<#assign lineNumber = conditionalElement.attributeValue("line-number")>

	selenium.sendLogger("${testCaseName?uncap_first}TestCase${lineNumber}", "pending");

	selenium.sendLogger("${testCaseName?uncap_first}TestCase${lineNumber}", "pass");

	<#assign thenElement = ifElement.element("then")>

	<#assign lineNumber = thenElement.attributeValue("line-number")>

	selenium.sendLogger("${testCaseName?uncap_first}TestCase${lineNumber}", "pending");

	<#assign blockElement = thenElement>

	<#include "test_case_block_element.ftl">

	<#assign lineNumber = thenElement.attributeValue("line-number")>

	selenium.sendLogger("${testCaseName?uncap_first}TestCase${lineNumber}", "pass");
}