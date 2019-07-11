<#include "security.ftl">
<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> User Name </label>
        <div class="col-sm-5">
            <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid','')}"
                   placeholder="User name" value="<#if user??>${user.username}</#if>"/>
            <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password </label>
        <div class="col-sm-5">
            <input type="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid','')}" placeholder="Password"/>
            <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password </label>
        <div class="col-sm-5">
            <input type="password" name="password2"
                   class="form-control ${(password2Error??)?string('is-invalid','')}" placeholder="Confirm password"/>
            <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email </label>
        <div class="col-sm-5">
            <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid','')}"
                   placeholder="Email" value="<#if user??>${user.email}</#if>"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
        </div>
    </div>
    <div class="col-sm-5">
        <div class="g-recaptcha" data-sitekey="6LfWGqkUAAAAAGzv3IJC3eKtZpIzXneXNI4n4ZEC"></div>
            <#if captchaError??>
                <div class="alert alert-danger" role="alert">
                    ${captchaError}
                </div>
            </#if>
    </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
    <button class="btn btn-primary mb-2" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-primary mb-2" type="submit"><#if user??>Sign Out<#else>Log in</#if></button>
</form>
</#macro>