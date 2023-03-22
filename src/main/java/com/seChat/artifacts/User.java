    package com.seChat.artifacts;

    import com.seChat.utils.EncodeTools;
    import org.bson.types.Binary;
    import org.bson.types.ObjectId;
    import org.springframework.data.mongodb.core.mapping.Document;
    import org.springframework.data.mongodb.core.mapping.MongoId;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.Collection;
    import java.util.Set;

    @Document(collection = "users")
    public class User implements UserDetails {
        private @MongoId
        ObjectId id;
        private String username;
        private String password;
        private long lastSeen;
        private Set<Role> userRoles;
        private Binary pubKey;
        private Binary pvtKey;

        public User(ObjectId id, String username, String password, long lastSeen, Set<Role> userRoles, Binary pubKey, Binary pvtKey) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.lastSeen = lastSeen;
            this.userRoles = userRoles;
            this.pubKey = pubKey;
            this.pvtKey = pvtKey;
        }

        public Binary getPubKey() {
            return pubKey;
        }

        public void setPubKey(Binary pubKey) {
            this.pubKey = pubKey;
        }

        public Binary getPvtKey() {
            return pvtKey;
        }

        public long getLastSeen() {
            return lastSeen;
        }

        public void setLastSeen(long lastSeen) {
            this.lastSeen = lastSeen;
        }

        public Set<Role> getUserRoles() {
            return userRoles;
        }

        public void setUserRoles(Set<Role> userRoles) {
            this.userRoles = userRoles;
        }

        public void setPvtKey(Binary pvtKey) {
            this.pvtKey = pvtKey;
        }

        public String getPvtKeyBase64() {
            return EncodeTools.BinaryToBase64(this.pvtKey);
        }

        public String getPubKeyBase64() {
            return EncodeTools.BinaryToBase64(this.pubKey);
        }

        public ObjectId getId() {
            return id;
        }

        public void setId(ObjectId id) {
            this.id = id;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return userRoles;
        }

        @Override
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }