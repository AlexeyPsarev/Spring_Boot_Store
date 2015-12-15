function postLoad()
{
    $('#' + category).parent().attr('class', 'active');
    $('#' + category).click(function(e){
       e.preventDefault(); 
    });
    $('#p' + page).parent().attr('class', 'active');
    $('#p' + page).click(function(e){
       e.preventDefault(); 
    });
}

$(document).ready(postLoad);
