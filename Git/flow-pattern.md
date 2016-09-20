git checkout <feature-branch>
git pull
git checkout <release-branch>
git pull
git merge --no-ff <feature-branch>
git push
git tag -a branch-<feature-branch> -m "Merge <feature-branch> into <release-branch>"
git push --tags
git branch -d <feature-branch>
git push origin :<feature-branch>
