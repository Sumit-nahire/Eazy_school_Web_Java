 <script>
        // Theme Toggle Logic
        const themeToggle = document.getElementById('theme-toggle');
        const body = document.body;
        const icon = themeToggle.querySelector('i');
        const logoSchool = document.getElementById('logo-school');
        const navbar = document.getElementById('navbar');

        themeToggle.addEventListener('click', () => {
            body.classList.toggle('dark-mode');
            const isDark = body.classList.contains('dark-mode');
            icon.className = isDark ? 'fas fa-sun' : 'far fa-moon';
            navbar.className = isDark ? 'flex items-center justify-between px-10 py-5 sticky top-0 bg-zinc-900 z-50 shadow-sm transition' : 'flex items-center justify-between px-10 py-5 sticky top-0 bg-white z-50 shadow-sm transition';
            logoSchool.className = isDark ? 'text-white' : 'text-gray-900';
        });

        // Counter Animation Logic
        const stats = document.querySelectorAll('[data-target]');
        const animateStats = () => {
            stats.forEach(counter => {
                const target = +counter.getAttribute('data-target');
                const count = +counter.innerText;
                const inc = target / 100;
                if (count < target) {
                    counter.innerText = Math.ceil(count + inc);
                    setTimeout(animateStats, 20);
                } else { counter.innerText = target.toLocaleString(); }
            });
        };

        const observer = new IntersectionObserver((entries) => {
            if (entries[0].isIntersecting) { animateStats(); observer.disconnect(); }
        }, { threshold: 0.5 });
        observer.observe(document.getElementById('stats-section'));
    </script>
