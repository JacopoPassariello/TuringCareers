$(document).ready(() => {
    const skillInput = $("#skill-input-text");
    skillInput.on('input', function() {
        $(this).css({
            'width': 'auto',
            'width': ($(this)[0].scrollWidth) + 'px'
        });
    }).trigger('input');

    let matchingSkills = ['Java', 'Javascript'];
})