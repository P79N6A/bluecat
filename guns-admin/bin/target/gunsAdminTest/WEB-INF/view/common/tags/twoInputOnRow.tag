@/*
    表单中input框标签中各个参数的说明:
    hidden1 : input hidden框的id
    id1 : input框id
    name1 : input框名称
    readonly1 : readonly属性
    changeEventFun1 : 点击事件的方法名
    onBlurFun1: 失去焦点事件
    onFocusFun1:得到焦点事件 
    style1 : 附加的css属性
    
    hidden2 : input hidden框的id
    id2 : input框id
    name2 : input框名称
    readonly2 : readonly属性
    changeEventFun2 : 点击事件的方法名
    onBlurFun2: 失去焦点事件
    onFocusFun2:得到焦点事件 
    style2 : 附加的css属性
@*/
<div class="form-group" style="margin-bottom:0px;">
    <label class="col-sm-3 control-label" style="width:12%;padding-left: 0px;padding-right: 0px;white-space:nowrap;">${name1}</label>
    <div class="col-sm-9" style="width:38%;">
        <input class="form-control" type="text" id="${id1}" name="${id1}"
               @if(isNotEmpty(value1)){
                    value="${tool.dateType(value1)}"
               @}
               @if(isNotEmpty(readonly1)){
                    readonly="${readonly1}"
               @}
               @if(isNotEmpty(changeEventFun1)){
                    onchange="${changeEventFun1}"
               @}
               @if(isNotEmpty(onBlurFun1)){
               		onBlur="${onBlurFun1}"
               @}
               @if(isNotEmpty(onFocusFun1)){
               		onFocus="${onFocusFun1}"
               @}
               @if(isNotEmpty(style1)){
                    style="${style1}"
               @}
               @if(isNotEmpty(disabled1)){
                    disabled="${disabled1}"
               @}
               autocomplete="off"
        >
        @if(isNotEmpty(hidden1)){
            <input class="form-control" type="hidden" id="${hidden1}" value="${hiddenValue1!}">
        @}
    </div>
    <label class="col-sm-3 control-label" style="width:12%;padding-left: 0px;padding-right: 0px;white-space:nowrap;">${name2}</label>
    <div class="col-sm-9" style="width:38%;">
        <input class="form-control" type="text" id="${id2}" name="${id2}"
               @if(isNotEmpty(value2)){
                    value="${tool.dateType(value2)}"
               @}
               @if(isNotEmpty(readonly2)){
                    readonly="${readonly2}"
               @}
               @if(isNotEmpty(changeEventFun2)){
                    onchange="${changeEventFun2}"
               @}
               @if(isNotEmpty(onBlurFun2)){
               		onBlur="${onBlurFun2}"
               @}
               @if(isNotEmpty(onFocusFun2)){
               		onFocus="${onFocusFun2}"
               @}
               @if(isNotEmpty(style2)){
                    style="${style2}"
               @}
               @if(isNotEmpty(disabled2)){
                    disabled="${disabled2}"
               @}
               autocomplete="off"
        >
        @if(isNotEmpty(hidden2)){
            <input class="form-control" type="hidden" id="${hidden2}" value="${hiddenValue2!}">
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


