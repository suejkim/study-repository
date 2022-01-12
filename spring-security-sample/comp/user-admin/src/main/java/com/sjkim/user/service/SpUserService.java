package com.sjkim.user.service;

import com.sjkim.user.domain.SpAuthority;
import com.sjkim.user.domain.SpUser;
import com.sjkim.user.repository.SpUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpUserService implements UserDetailsService {

    private final SpUserRepository spUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return spUserRepository.findSpUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Optional<SpUser> findUser(String email) {
        return spUserRepository.findSpUserByEmail(email);
    }

    public SpUser save(SpUser spUser) {
        return spUserRepository.save(spUser);
    }

    public boolean addAuthority(Long userId, String authority) {
        var userOptional = spUserRepository.findById(userId);
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            SpAuthority newRole = new SpAuthority(user.getUserId(), authority);
            if (user.getAuthorities() == null) {
                HashSet<SpAuthority> authorities = new HashSet<>();
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);
            } else if (!user.getAuthorities().contains(newRole)) {
                HashSet<SpAuthority> authorities = new HashSet<>();
                authorities.addAll(user.getAuthorities());
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);
            }
        }
        return true;
    }

    public boolean addAuthority(SpUser user, String authority) {
        SpAuthority newRole = new SpAuthority(user.getUserId(), authority);
        if (user.getAuthorities() == null) {
            HashSet<SpAuthority> authorities = new HashSet<>();
            authorities.add(newRole);
            user.setAuthorities(authorities);
            save(user);
        }
        return true;
    }

    public boolean removeAuthority(Long userId, String authority) {
        spUserRepository.findById(userId).ifPresent(user -> {
            if (user.getAuthorities() == null) {
                return;
            }
            SpAuthority targetRole = new SpAuthority(user.getUserId(), authority);
            if (user.getAuthorities().contains(targetRole)) {
                user.setAuthorities(
                        user.getAuthorities().stream().filter(auth -> !auth.equals(targetRole)).collect(Collectors.toSet())
                );
                save(user);
            }
        });
        return true;
    }
}
