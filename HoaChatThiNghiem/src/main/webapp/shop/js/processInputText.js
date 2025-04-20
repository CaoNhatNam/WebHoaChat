function notEmpty(inputText, notify) {
    var result = false;
    inputText.onblur = function (){
        var valueOfText = inputText.value;
        var checkValue = valueOfText.trim() ? undefined : "Vui lòng nhập trường này";
        if(checkValue != undefined){
            notify.innerHTML = checkValue;
            result = false;
        }else{
            notify.innerHTML = "";
            result = true;
        }
    }
    return result;
}
function notEmptyAndMinimum(inputText, notify, text) {
    var result = false;
    inputText.onblur = function (){
        var valueOfText = inputText.value;
        var checkValue = valueOfText.trim() ? undefined : "Vui lòng nhập trường này";
        if(checkValue != undefined){
            notify.innerHTML = checkValue;
            result = false;
        }else{
            if(valueOfText.length < 8){
                notify.innerHTML = text + " phải tối thiểu 8 kí tự"
                result = false;
            }else{
                notify.innerHTML = "";
                result = true;
            }
        }
    }
    return result;
}

function preventSubmitForRegister(form, ...conditions){
    form.addEventListener('submit', (event)=>{
        var result = false;
        for(var i = 0; i < conditions.length; i++){
            var valueElement = conditions[i].value.trim();
            if(valueElement == ""){
                result = false;
                break ;
            }else{
                if(conditions[i].type === "password"){
                    if(valueElement.length < 8){
                        result = false;
                        break;
                    }else{
                        result = true;
                        continue;
                    }
                }else{
                    result = true;
                    continue;
                }
            }
        }
        if(result == false){
            event.preventDefault();
        }
    })
}