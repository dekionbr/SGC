$(document)
		.ready(
				function() {

					var $body = $('body'), $content = $('#content'), $form = $content
							.find('#loginform');

					// IE doen't like that fadein
					if (!$.browser.msie)
						$body.fadeTo(0, 0.0).delay(500).fadeTo(1000, 1);

					$("input").uniform();

					$form
							.wl_Form({
								status : false,
								onBeforeSubmit : function(data) {
									$form.wl_Form('set', 'sent', false);
									if (!data.username || !data.password) {
										$.wl_Alert('Inclua os dados de acesso',
												'info', '#content');
										return false;
									}
								},
								onSuccess : function(data, textStatus, jqXHR) {
									var obj = JSON.parse(data);
									if (obj != undefined && obj.Erro != null) {
										$
												.wl_Alert(obj.Erro, 'info',
														'#content');
									} else {
										if (obj != undefined && obj.Url != null)
											window.location = "Dashboard";
										else
											$
													.wl_Alert(
															"Erro ao logar, tente novamente mais tarde",
															'info', '#content');
									}

									return false
								}
							});

				});