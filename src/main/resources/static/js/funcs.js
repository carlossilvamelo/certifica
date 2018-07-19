function setBackground(){

    var radioValue = $("input[name='gender']:checked").val();

    var url = "url('img/"+radioValue+".jpg')";

    if(radioValue != null){
        if(radioValue =='none'){
            $('#bdContent').css("background-image", 'none');
        }else{
            $('#bdContent').css("background-image", url);
        }



    }
    else
        alert("Escolha uma opção de fundo.");
}

function verifyTags(){

    var text =$("#editor").text();

    //  var text = 'as {{País}} sa {{Nome}} asdasd {{Instituição}} asdasd {{casa}}';
    text = text.toUpperCase();

    var indexEndTag, indexBeginTag;
    var beginCount=0, endCount=0;

    var start = false;

    for (var i in text){
        if(text[i] == '{'){
            beginCount++;
            if(beginCount == 2){
                indexBeginTag = i;
                indexBeginTag++;
                start = true;
            }

        }

        if((text[i] == '}')){
            if(start){
                indexEndTag = i;



                if((text.substring(indexBeginTag,indexEndTag) == "NOME")||
                    (text.substring(indexBeginTag,indexEndTag) == "PAÍS")||
                    (text.substring(indexBeginTag,indexEndTag) == "INSTITUIÇÃO")||
                    (text.substring(indexBeginTag,indexEndTag) == "EMAIL")||
                    (text.substring(indexBeginTag,indexEndTag) == "INSCRIÇÃO")){
                    console.log('tag existe: '+text.substring(indexBeginTag,indexEndTag));
                    start=false;
                    beginCount=0; endCount=0;

                }else{
                    console.log('tag NÃO existe: '+text.substring(indexBeginTag,indexEndTag));
                    var tag = '{{'+text.substring(indexBeginTag,indexEndTag).toLowerCase()+'}}';
                    alert('A tag '+tag+' não existe!');
                    return false;
                    start=false;
                    beginCount=0; endCount=0;
                }

            }

        }

    }

    return true;
}

function sendCertificate(){
console.log($("#members").val())
console.log($("#editor").text())

    if(verifyTags()){
        var radioValue = $("input[name='gender']:checked").val();
        var url = "url('img/"+radioValue+".jpg')"

        if($("#members").val() == ""){
            alert("É necessário informar os dados dos partifipantes!");
        }else{

            $.ajax({
                type:"POST",
                url: "/send",
                data: { text: $("#editor").text(),
                    members: $("#members").val(),
                    imageUrl: url
                },
                success: function (data) {
                    alert("Os emails estão sendo enviados!");
                }
            });

        }

    }
}

$(".image-radio img").click(function(){
    $(this).prev().attr('checked',true);
    $('#certificate-background').css("background-image", "url('img/bg1.jpg')");
})

