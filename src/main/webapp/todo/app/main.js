/**
 * Created by andrey on 29.12.2015.
 */
window.onload=function(){
  showTaskList();
  function showTaskList () {
      $.ajax({
          type: "GET",
          url: "/todo",
          error: function (jqXHR) {
              var errorPage=jqXHR.responseText;
              var textError =$(errorPage).filter("h1").text();
              alert(textError);
          },
          success: function (data) {

              createTaskList(data);


          }
      });
  }


     function createTaskList(tList) {
         $('#list-tasks').remove();
         $('#add-task').remove();
         $('<div>',{id:'list-tasks'}).prependTo('#main');
         for (var i = 0; i < tList.length; i++) {
             console.log(tList[i]);

             $('<div>', {id: 'taskId_' +i}).appendTo('#list-tasks');
             $('<span/>', {text: 'ID: ' + tList[i].id}).appendTo('#taskId_' + i);
             $('<span/>', {text: 'Date Creation: ' + tList[i].dateCreation}).appendTo('#taskId_' + i);
             $('<span/>', {text: 'Target Date: ' + tList[i].targetDate}).appendTo('#taskId_' + i);
             $('<span/>', {text: 'Text: ' + tList[i].text}).appendTo('#taskId_' + i);
             $('<span/>', {
                 id: i, text: "update",class: "action-task" ,
                 click: function () {
                     $('#update-commit').remove();
                     $("#update-task").css("display", "block");
                     $('input[name="id-update"]').val(tList[this.id].id);
                     $('input[name="dateCreation-update"]').val(tList[this.id].dateCreation);
                     $('input[name="targetDate-update"]').val(tList[this.id].targetDate);
                     $('input[name="text-update"]').val(tList[this.id].text);
                     $('<span/>',{id:"update-commit", text:"Update", class: "action-task" ,
                         click:function(){
                            var DataToSend = {
                                id: $('input[name="id-update"]').val(),
                                targetDate: $('input[name="targetDate-update"]').val(),
                                dateCreation:  $('input[name="dateCreation-update"]').val(),
                                text: $('input[name="text-update"]').val()
                             };

                             $.ajax({
                                 type: "PUT",
                                 contentType: "application/json; charset=utf-8",
                                 url: "/todo",
                                 data: JSON.stringify(DataToSend),

                                 error: function (jqXHR) {
                                     var errorPage=jqXHR.responseText;
                                     var textError =$(errorPage).filter("h1").text();
                                     alert(textError);
                                 },
                                 success: function () {
                                     $("#update-task").css("display", "none");
                                     showTaskList();

                                 }
                             });
                       }}).appendTo("#update-task");
                 }
             }).css("margin-right", "20px").appendTo('#taskId_' + i);
           $('<span/>',{id: "d"+tList[i].id,text:"delete",class: "action-task" ,
                      click: function () {
                           $.ajax({
                               type: "DELETE",
                               url: "/todo",
                               data: this.id.slice(1),
                               error: function (jqXHR) {
                                   var errorPage=jqXHR.responseText;
                                   var textError =$(errorPage).filter("h1").text();
                                   alert(textError);
                               },
                               success: function () {
                                   showTaskList();

                               }
                           });
                        }}).appendTo('#taskId_' + i);
         }
         $("<div/>", {text: "--------add Task---------", id: "add-task", class: "action-task" ,
             click: function () {

                 $("#create-task").css("display", "block");
             }
         }).prependTo("#create-header");

     }
}
